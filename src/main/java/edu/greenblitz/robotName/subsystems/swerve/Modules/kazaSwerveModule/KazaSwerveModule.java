package edu.greenblitz.robotName.subsystems.swerve.Modules.kazaSwerveModule;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.subsystems.swerve.Modules.ISwerveModule;
import edu.greenblitz.robotName.subsystems.swerve.Modules.SwerveModuleInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.swerve.SwerveModuleConfigObject;
import edu.greenblitz.robotName.utils.Conversions;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.AnalogInput;

import static edu.greenblitz.robotName.RobotConstants.General.Motors.NEO_PHYSICAL_TICKS_TO_RADIANS;

public class KazaSwerveModule implements ISwerveModule {

    private final GBSparkMax angleMotor;
    private final GBSparkMax linearMotor;
    private final AnalogInput lamprey;
    private final SimpleMotorFeedforward linearFeedForward;
    SwerveChassis.Module module;

    public KazaSwerveModule (SwerveChassis.Module module){


        int angleMotorID;
        int linearMotorID;
        int lampreyID;
        boolean linInverted;
        this.module = module;

        SwerveModuleConfigObject configObject = switch (module) {
            case BACK_RIGHT -> KazaSwerveConstants.KAZA_SWERVE_MODULE_BACK_RIGHT;
            case BACK_LEFT -> KazaSwerveConstants.KAZA_SWERVE_MODULE_BACK_LEFT;
            case FRONT_RIGHT -> KazaSwerveConstants.KAZA_SWERVE_MODULE_FRONT_RIGHT;
            case FRONT_LEFT -> KazaSwerveConstants.KAZA_SWERVE_MODULE_FRONT_LEFT;
            default -> throw new IllegalArgumentException("Invalid swerve module");
        };

        angleMotorID = configObject.angleMotorID;
        linearMotorID = configObject.linearMotorID;
        lampreyID = configObject.AbsoluteEncoderID;
        linInverted = configObject.linInverted;

        angleMotor = new GBSparkMax(angleMotorID, CANSparkMaxLowLevel.MotorType.kBrushless);
        angleMotor.config(KazaSwerveConstants.BASE_ANGULAR_MOTOR_CONFIG_OBJECT);
        angleMotor.getPIDController().setPositionPIDWrappingEnabled(true);
        angleMotor.getPIDController().setPositionPIDWrappingMaxInput(2* Math.PI);
        angleMotor.getPIDController().setPositionPIDWrappingMinInput(0);

        linearMotor = new GBSparkMax(linearMotorID, CANSparkMaxLowLevel.MotorType.kBrushless);
        linearMotor.config(KazaSwerveConstants.BASE_LINEAR_CONFIG_OBJECT.withInverted(linInverted));

        lamprey = new AnalogInput(lampreyID);
        lamprey.setAverageBits(KazaSwerveConstants.LAMPREY_AVERAGE_BITS);
        this.linearFeedForward = new SimpleMotorFeedforward(KazaSwerveConstants.ks, KazaSwerveConstants.kv, KazaSwerveConstants.ka);


    }


    @Override
    public void setLinearVelocity(double speed) {
        linearMotor.getPIDController().setReference(speed, CANSparkMax.ControlType.kVelocity, 0, linearFeedForward.calculate(speed));
    }

    @Override
    public void rotateToAngle(Rotation2d angle) {
        angleMotor.getPIDController().setReference(angle.getRadians(), CANSparkMax.ControlType.kPosition);
    }

    @Override
    public void setLinearVoltage(double voltage) {
        linearMotor.setVoltage(voltage);
    }

    @Override
    public void setAngularVoltage(double voltage) {
        angleMotor.setVoltage(voltage);
    }

    @Override
    public void setLinearIdleModeBrake(boolean isBrake) {
        linearMotor.setIdleMode(isBrake ? CANSparkMax.IdleMode.kBrake : CANSparkMax.IdleMode.kCoast);
    }

    @Override
    public void setAngularIdleModeBrake(boolean isBrake) {
        angleMotor.setIdleMode(isBrake ? CANSparkMax.IdleMode.kBrake : CANSparkMax.IdleMode.kCoast);
    }

    @Override
    public void resetAngle(Rotation2d angle) {
        angleMotor.getEncoder().setPosition(angle.getRadians());
    }

    @Override
    public void stop() {
        setLinearVoltage(0);
        setAngularVoltage(0);
    }

    @Override
    public void updateInputs(SwerveModuleInputsAutoLogged inputs) {
        inputs.absoluteEncoderPosition = KazaSwerveLampreyInterpulationMaps.CALIBRATION_DATASETS.get(module).get(lamprey.getVoltage()) * NEO_PHYSICAL_TICKS_TO_RADIANS/ KazaSwerveConstants.ANGULAR_GEAR_RATIO;
        inputs.isAbsoluteEncoderConnected = lamprey.getVoltage() != 0; //analog input returns 0;

        inputs.linearCurrent = linearMotor.getOutputCurrent();
        inputs.angularCurrent = angleMotor.getOutputCurrent();

        inputs.linearVoltage = linearMotor.getAppliedOutput();
        inputs.angularVoltage = angleMotor.getAppliedOutput();

        inputs.linearVelocity = Conversions.convertRPMToRadiansPerSec(linearMotor.getEncoder().getVelocity() * KazaSwerveConstants.ANGULAR_TICKS_TO_WHEEL_TO_RPM);
        inputs.angularVelocity = angleMotor.getEncoder().getVelocity();

        inputs.linearMetersPassed = linearMotor.getEncoder().getPosition();
        inputs.angularPositionRadians = angleMotor.getEncoder().getPosition();


    }
}
