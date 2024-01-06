package edu.greenblitz.robotName.subsystems.swerve.Modules.mk4iSwerveModule;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.sensors.CANCoder;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.subsystems.swerve.Modules.ISwerveModule;
import edu.greenblitz.robotName.subsystems.swerve.Modules.SwerveModuleInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.swerve.SwerveModuleConfigObject;
import edu.greenblitz.robotName.utils.Conversions;
import edu.greenblitz.robotName.utils.motors.GBFalcon;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class MK4ISwerveModule implements ISwerveModule {

    private final GBFalcon angularMotor;
    private final GBFalcon linearMotor;
    private final CANCoder canCoder;
    private final SimpleMotorFeedforward linearFeedForward;
    private final double encoderOffset;


    public MK4ISwerveModule(SwerveChassis.Module module) {

        SwerveModuleConfigObject configObject = switch (module) {
            case FRONT_LEFT -> MK4iSwerveConstants.MK4I_MODULE_FRONT_LEFT;
            case FRONT_RIGHT -> MK4iSwerveConstants.MK4I_MODULE_FRONT_RIGHT;
            case BACK_LEFT -> MK4iSwerveConstants.MK4I_MODULE_BACK_LEFT;
            case BACK_RIGHT -> MK4iSwerveConstants.MK4I_MODULE_BAK_RIGHT;
            default -> throw new IllegalArgumentException("Invalid swerve module");
        };

        angularMotor = new GBFalcon(configObject.angleMotorID);
        angularMotor.config(new GBFalcon.FalconConfObject(MK4iSwerveConstants.ANGULAR_FALCON_CONFIG_OBJECT));

        linearMotor = new GBFalcon(configObject.linearMotorID);
        linearMotor.config(new GBFalcon.FalconConfObject(MK4iSwerveConstants.LINEAR_FALCON_CONF_OBJECT).withInverted(configObject.linInverted));

        canCoder = new CANCoder(configObject.AbsoluteEncoderID);
        this.encoderOffset = configObject.encoderOffset.getRotations();

        this.linearFeedForward = new SimpleMotorFeedforward(MK4iSwerveConstants.ks, MK4iSwerveConstants.kv, MK4iSwerveConstants.ka);
    }


    @Override
    public void setLinearVelocity(double speed) {
        linearMotor.set(
                TalonFXControlMode.Velocity,
                speed / MK4iSwerveConstants.LINEAR_TICKS_TO_METERS_PER_SECOND,
                DemandType.ArbitraryFeedForward,
                linearFeedForward.calculate(speed) / Battery.getInstance().getCurrentVoltage());

    }

    @Override
    public void rotateToAngle(Rotation2d angle) {
        angularMotor.set(ControlMode.Position, Conversions.MK4IConversions.convertRadiansToTicks(angle));
    }

    @Override
    public void setLinearVoltage(double voltage) {
        linearMotor.set(ControlMode.PercentOutput, voltage / Battery.getInstance().getCurrentVoltage());
    }

    @Override
    public void setAngularVoltage(double voltage) {
        angularMotor.set(ControlMode.PercentOutput, voltage / Battery.getInstance().getCurrentVoltage());
    }

    @Override
    public void setLinearIdleModeBrake(boolean isBrake) {
        linearMotor.setNeutralMode(isBrake ? NeutralMode.Brake : NeutralMode.Coast);
    }

    @Override
    public void setAngularIdleModeBrake(boolean isBrake) {
        angularMotor.setNeutralMode(isBrake ? NeutralMode.Brake : NeutralMode.Coast);
    }

    @Override
    public void resetAngle(Rotation2d angle) {
        angularMotor.setSelectedSensorPosition(Conversions.MK4IConversions.convertRadiansToTicks(angle));
    }

    @Override
    public void stop() {
        linearMotor.set(ControlMode.PercentOutput, 0);
        angularMotor.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void updateInputs(SwerveModuleInputsAutoLogged inputs) {
        inputs.linearVelocity = Conversions.MK4IConversions.convertSensorVelocityToMeterPerSecond(linearMotor.getSelectedSensorVelocity());
        inputs.angularVelocity =  Conversions.MK4IConversions.convertSensorVelocityToRPM(angularMotor.getSelectedSensorVelocity());

        inputs.linearVoltage = linearMotor.getMotorOutputVoltage();
        inputs.angularVoltage = angularMotor.getMotorOutputVoltage();

        inputs.linearCurrent = linearMotor.getSupplyCurrent();
        inputs.angularCurrent = angularMotor.getStatorCurrent();

        inputs.linearMetersPassed = Conversions.MK4IConversions.convertTicksToMeters(linearMotor.getSelectedSensorPosition());
        inputs.angularPositionRadians = Conversions.MK4IConversions.convertTicksToRadians(angularMotor.getSelectedSensorPosition());

        if (Double.isNaN(Units.degreesToRadians(canCoder.getAbsolutePosition()))){
            inputs.absoluteEncoderPosition = 0;
        }else{
            inputs.absoluteEncoderPosition = Units.degreesToRadians(canCoder.getAbsolutePosition()) - Units.rotationsToRadians(encoderOffset);
        }
        inputs.isAbsoluteEncoderConnected = canCoder.getFirmwareVersion() != -1;
    }
}
