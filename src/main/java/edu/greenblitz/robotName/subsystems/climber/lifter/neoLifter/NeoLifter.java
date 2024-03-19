package edu.greenblitz.robotName.subsystems.climber.lifter.neoLifter;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.climber.lifter.ILifter;
import edu.greenblitz.robotName.subsystems.climber.lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.climber.lifter.LifterInputsAutoLogged;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class NeoLifter implements ILifter {

    private GBSparkMax motor;

    public NeoLifter() {
        motor = new GBSparkMax(NeoLifterConstants.MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
        motor.config(NeoLifterConstants.CONFIG);

        motor.getReverseLimitSwitch(NeoLifterConstants.BACKWARD_LIMIT_SWITCH_TYPE)
                .enableLimitSwitch(NeoLifterConstants.IS_BACKWARD_LIMIT_SWITCH_ENABLED);
        motor.getForwardLimitSwitch(NeoLifterConstants.FORWARD_LIMIT_SWITCH_TYPE)
                .enableLimitSwitch(NeoLifterConstants.IS_FORWARD_LIMIT_SWITCH_ENABLED);
        motor.setSoftLimit(
                CANSparkMax.SoftLimitDirection.kReverse,
                LifterConstants.BACKWARD_LIMIT
        );
        motor.setSoftLimit(
                CANSparkMax.SoftLimitDirection.kForward,
                LifterConstants.FORWARD_LIMIT
        );
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
    public void resetEncoder(double position) {
        motor.getEncoder().setPosition(position);
    }

    @Override
    public void stop() {
        setPower(0);
    }

    @Override
    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        motor.setIdleMode(idleMode);
    }

    @Override
    public void goToPosition(double position) {
        motor.getPIDController().setReference(
                position,
                CANSparkMax.ControlType.kPosition,
                NeoLifterConstants.PID_SLOT,
                NeoLifterConstants.FEED_FORWARD.calculate(position)
        );
    }

    @Override
    public void updateInputs(LifterInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.position = motor.getEncoder().getPosition();
        inputs.velocity = motor.getEncoder().getVelocity();
        inputs.isForwardSwitchPressed = motor.getForwardLimitSwitch(NeoLifterConstants.FORWARD_LIMIT_SWITCH_TYPE).isPressed();
        inputs.isBackwardSwitchPressed = motor.getReverseLimitSwitch(NeoLifterConstants.BACKWARD_LIMIT_SWITCH_TYPE).isPressed();
    }
}