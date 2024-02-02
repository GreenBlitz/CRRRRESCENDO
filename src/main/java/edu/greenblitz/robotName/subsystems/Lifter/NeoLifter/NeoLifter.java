package edu.greenblitz.robotName.subsystems.Lifter.NeoLifter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.subsystems.Lifter.ILifter;
import edu.greenblitz.robotName.subsystems.Lifter.LifterInputsAutoLogged;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;

import static edu.greenblitz.robotName.subsystems.Lifter.NeoLifter.NeoLifterConstants.*;

public class NeoLifter implements ILifter {

    private GBSparkMax motor;

    public NeoLifter() {
        motor = new GBSparkMax(NeoLifterConstants.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor.config(NeoLifterConstants.CONFIG);

        motor.getReverseLimitSwitch(FORWARD_LIMIT_SWITCH_TYPE).enableLimitSwitch(true);
        motor.getForwardLimitSwitch(BACKWARD_LIMIT_SWITCH_TYPE).enableLimitSwitch(true);
        motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
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
    public void stopMotor() {
        this.setPower(0);
    }

    @Override
    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        motor.setIdleMode(idleMode);
    }

    @Override
    public void goToPosition(Rotation2d position) {
        motor.getPIDController().setReference(position.getRadians(), CANSparkMax.ControlType.kPosition, 0 , FEED_FORWARD.calculate(position.getRadians()));
    }

    @Override
    public void updateInputs(LifterInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.position = Rotation2d.fromRadians(motor.getEncoder().getPosition());
        inputs.velocity = motor.getEncoder().getVelocity();
        inputs.isForwardSwitchPressed = motor.getForwardLimitSwitch(FORWARD_LIMIT_SWITCH_TYPE).isPressed();
        inputs.isBackwardSwitchPressed = motor.getReverseLimitSwitch(BACKWARD_LIMIT_SWITCH_TYPE).isPressed();
    }
}
