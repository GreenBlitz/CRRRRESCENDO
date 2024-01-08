package edu.greenblitz.robotName.subsystems.swerve.Modules.mk4iSwerveModule;

import com.ctre.phoenix.sensors.CANCoder;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.controls.MotionMagicExpoDutyCycle;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.subsystems.swerve.Modules.ISwerveModule;
import edu.greenblitz.robotName.subsystems.swerve.Modules.SwerveModuleInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.swerve.SwerveModuleConfigObject;
import edu.greenblitz.robotName.utils.Conversions;
import edu.greenblitz.robotName.utils.motors.GBTalonFXPro;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class MK4ISwerveModule implements ISwerveModule {

    private final GBTalonFXPro angularMotor;
    private final GBTalonFXPro linearMotor;
    private final CANCoder canCoder;
    private final SimpleMotorFeedforward linearFeedForward;
    private final double encoderOffset;

    VelocityTorqueCurrentFOC velocityTorqueCurrentFOC = new VelocityTorqueCurrentFOC(0);
    MotionMagicExpoDutyCycle motionMagicExpoDutyCycle = new MotionMagicExpoDutyCycle(0)
            .withEnableFOC(false)
            .withOverrideBrakeDurNeutral(true);
    public MK4ISwerveModule(SwerveChassis.Module module) {

        SwerveModuleConfigObject configObject = switch (module) {
            case FRONT_LEFT -> MK4iSwerveConstants.MK4I_MODULE_FRONT_LEFT;
            case FRONT_RIGHT -> MK4iSwerveConstants.MK4I_MODULE_FRONT_RIGHT;
            case BACK_LEFT -> MK4iSwerveConstants.MK4I_MODULE_BACK_LEFT;
            case BACK_RIGHT -> MK4iSwerveConstants.MK4I_MODULE_BAK_RIGHT;
            default -> throw new IllegalArgumentException("Invalid swerve module");
        };

        angularMotor = new GBTalonFXPro(configObject.angleMotorID);
        angularMotor.getConfigurator().apply(MK4iSwerveConstants.ANGULAR_FALCON_CONFIG_OBJECT);

        linearMotor = new GBTalonFXPro(configObject.linearMotorID);
        linearMotor.getConfigurator().apply(MK4iSwerveConstants.LINEAR_FALCON_CONFIG_OBJECT);
        linearMotor.setInverted(configObject.linInverted);

        canCoder = new CANCoder(configObject.AbsoluteEncoderID);

        FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();
        FEEDBACK_CONFIGS.FeedbackRemoteSensorID = canCoder.getDeviceID();
        FEEDBACK_CONFIGS.FeedbackSensorSource = FeedbackSensorSourceValue.SyncCANcoder;
        FEEDBACK_CONFIGS.RotorToSensorRatio = MK4iSwerveConstants.ANGULAR_GEAR_RATIO;

        angularMotor.getConfigurator().refresh(FEEDBACK_CONFIGS);

        this.encoderOffset = configObject.encoderOffset.getRotations();

        this.linearFeedForward = new SimpleMotorFeedforward(MK4iSwerveConstants.ks, MK4iSwerveConstants.kv, MK4iSwerveConstants.ka);
    }


    @Override
    public void setLinearVelocity(double speed) {
        linearMotor.setControl(velocityTorqueCurrentFOC.withVelocity(speed));
    }

    @Override
    public void rotateToAngle(Rotation2d angle) {
        angularMotor.setControl(motionMagicExpoDutyCycle.withPosition(angle.getRadians()));
    }

    @Override
    public void setLinearVoltage(double voltage) {
        linearMotor.setVoltage(voltage);
    }

    @Override
    public void setAngularVoltage(double voltage) {
        angularMotor.setVoltage(voltage);
    }

    @Override
    public void setLinearIdleModeBrake(boolean isBrake) {
        linearMotor.setNeutralMode(isBrake ? NeutralModeValue.Brake : NeutralModeValue.Coast);
    }

    @Override
    public void setAngularIdleModeBrake(boolean isBrake) {
        angularMotor.setNeutralMode(isBrake ? NeutralModeValue.Brake : NeutralModeValue.Coast);
    }

    @Override
    public void resetAngle(Rotation2d angle) {
        angularMotor.setPosition(angle.getRotations());
    }


    @Override
    public void stop() {
        linearMotor.stopMotor();
        angularMotor.stopMotor();
    }

    @Override
    public void updateInputs(SwerveModuleInputsAutoLogged inputs) {
        inputs.linearVelocity = linearMotor.getVelocity().getValue();
        inputs.angularVelocity = Conversions.MK4IConversions.convertSensorVelocityToRPM(angularMotor.getVelocity().getValue());

        inputs.linearVoltage = linearMotor.getSupplyVoltage().getValue();
        inputs.angularVoltage = angularMotor.getSupplyVoltage().getValue();

        inputs.linearCurrent = linearMotor.getSupplyCurrent().getValue();
        inputs.angularCurrent = angularMotor.getStatorCurrent().getValue();

        inputs.linearMetersPassed = Conversions.MK4IConversions.convertTicksToMeters(linearMotor.getPosition().getValue());
        inputs.angularPositionRadians = Conversions.MK4IConversions.convertTicksToRadians(angularMotor.getPosition().getValue());

        if (Double.isNaN(Units.degreesToRadians(canCoder.getAbsolutePosition()))) {
            inputs.absoluteEncoderPosition = 0;
        } else {
            inputs.absoluteEncoderPosition = Units.degreesToRadians(canCoder.getAbsolutePosition()) - Units.rotationsToRadians(encoderOffset);
        }
        inputs.isAbsoluteEncoderConnected = canCoder.getFirmwareVersion() != -1;
    }
}
