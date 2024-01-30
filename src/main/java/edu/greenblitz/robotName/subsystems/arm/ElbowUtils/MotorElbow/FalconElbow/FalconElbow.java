package edu.greenblitz.robotName.subsystems.arm.ElbowUtils.MotorElbow.FalconElbow;

import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.IElbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.MotorElbow.MotorElbowConstants;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;

public class FalconElbow implements IElbow {

    private TalonFX motor;

    private MotionMagicDutyCycle motionMagicDutyCycle;

    public FalconElbow() {
        motor = new TalonFX(MotorElbowConstants.MOTOR_ID);
        motor.getConfigurator().apply(FalconElbowConstants.TALON_FX_CONFIGURATION);
        motor.setNeutralMode(FalconElbowConstants.NEUTRAL_MODE_VALUE);
        motor.optimizeBusUtilization();
        motionMagicDutyCycle = new MotionMagicDutyCycle(0, true, getFeedForward().calculate(0), 0, true, true, true);
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
                motionMagicDutyCycle.withPosition(targetAngle.getRadians() / ElbowConstants.RELATIVE_POSITION_CONVERSION_FACTOR)
                .withFeedForward(getFeedForward().calculate(motor.getVelocity().getValue() * ElbowConstants.RELATIVE_VELOCITY_CONVERSION_FACTOR )));
    }

    @Override
    public SimpleMotorFeedforward getFeedForward() {
        return FalconElbowConstants.SIMPLE_MOTOR_FEED_FORWARD;
    }

    @Override
    public void updateInputs(ElbowInputsAutoLogged inputs) {
        inputs.outputCurrent = motor.getSupplyCurrent().getValue();
        inputs.appliedOutput = motor.getMotorVoltage().getValue();
        inputs.position = motor.getPosition().getValue() * ElbowConstants.RELATIVE_POSITION_CONVERSION_FACTOR;
        inputs.velocity = motor.getVelocity().getValue() * ElbowConstants.RELATIVE_VELOCITY_CONVERSION_FACTOR;
        inputs.absoluteEncoderPosition = motor.getDutyCycle().getValue() * ElbowConstants.ABSOLUTE_POSITION_CONVERSION_FACTOR;
        inputs.temperature = motor.getDeviceTemp().getValue();
        inputs.hasReachedForwardLimit = motor.getFault_ForwardSoftLimit().getValue();
        inputs.hasReachedBackwardLimit = motor.getFault_ReverseSoftLimit().getValue();
    }
}
