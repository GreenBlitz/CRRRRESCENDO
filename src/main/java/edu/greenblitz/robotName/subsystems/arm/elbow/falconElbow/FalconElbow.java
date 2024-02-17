package edu.greenblitz.robotName.subsystems.arm.elbow.falconElbow;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.arm.elbow.IElbow;
import edu.greenblitz.robotName.utils.motors.GBTalonFXPro;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

import static edu.greenblitz.robotName.RobotConstants.General.Motors.IS_SWITCH_CLOSED;

public class FalconElbow implements IElbow {

    private GBTalonFXPro motor;

    private DutyCycleEncoder absoluteEncoder;

    private MotionMagicDutyCycle motionMagicDutyCycle;

    private PositionVoltage positionVoltage;

    public FalconElbow() {
        motor = new GBTalonFXPro(FalconElbowConstants.MOTOR_ID, FalconElbowConstants.CANBUS_CHAIN);
        motor.applyConfiguration(FalconElbowConstants.TALON_FX_CONFIGURATION);
        motor.setNeutralMode(FalconElbowConstants.NEUTRAL_MODE_VALUE);
        optimizeCanBusUtilization(motor);

        absoluteEncoder = new DutyCycleEncoder(FalconElbowConstants.ABSOLUTE_ENCODER_CHANNEL);

        resetAngle(Rotation2d.fromRotations(absoluteEncoder.getAbsolutePosition()));

        positionVoltage = new PositionVoltage(motor.getPosition().getValue());
        motionMagicDutyCycle = new MotionMagicDutyCycle(motor.getPosition().getValue());
    }

    public void optimizeCanBusUtilization(GBTalonFXPro motor) {
        BaseStatusSignal.setUpdateFrequencyForAll(
                RobotConstants.General.Motors.SIGNAL_FREQUENCY_HERTZ,
                motor.getPosition(),
                motor.getVelocity(),
                motor.getMotorVoltage(),
                motor.getVelocity(),
                motor.getAcceleration()
        );
        motor.optimizeBusUtilization();
    }

    @Override
    public void setPower(double power) {
        motor.set(power);
    }

    @Override
    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);

    }

    @Override
    public void setIdleMode(NeutralModeValue idleMode) {
        motor.setNeutralMode(idleMode);
    }

    @Override
    public void resetAngle(Rotation2d position) {
        motor.setPosition(position.getRadians());
    }

    @Override
    public void moveToAngle(Rotation2d targetAngle) {
        motor.setControl(
                motionMagicDutyCycle
                        .withPosition(targetAngle.getRotations())
                        .withEnableFOC(true)
                        .withSlot(FalconElbowConstants.MOTION_MAGIC_PID_SLOT)
                        .withLimitForwardMotion(true)
                        .withLimitReverseMotion(true)
                        .withOverrideBrakeDurNeutral(true)
        );
    }

    @Override
    public void standInPlace(Rotation2d targetAngle) {
        motor.setControl(
                positionVoltage
                        .withPosition(targetAngle.getRotations())
                        .withSlot(FalconElbowConstants.STAND_IN_PLACE_PID_SLOT)
                        .withLimitForwardMotion(true)
                        .withLimitReverseMotion(true)
                        .withEnableFOC(true)
                        .withOverrideBrakeDurNeutral(true)
        );
    }

    @Override
    public void updateInputs(ElbowInputsAutoLogged inputs) {
        inputs.outputCurrent = motor.getSupplyCurrent().getValue();
        inputs.appliedOutput = motor.getMotorVoltage().getValue();
        inputs.position = Rotation2d.fromRadians(BaseStatusSignal.getLatencyCompensatedValue(motor.getPosition(), motor.getVelocity()));
        inputs.velocity = motor.getVelocity().getValue();
        inputs.acceleration = motor.getAcceleration().getValue();
        inputs.absoluteEncoderPosition = absoluteEncoder.getAbsolutePosition();
        inputs.hasReachedForwardLimit = motor.getForwardLimit().getValue().value == IS_SWITCH_CLOSED;
        inputs.hasReachedBackwardLimit = motor.getReverseLimit().getValue().value == IS_SWITCH_CLOSED;
    }
}