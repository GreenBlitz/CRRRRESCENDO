package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj.DigitalInput;

public class NeoLifter implements ILifter {
    private GBSparkMax motor;
    private ProfiledPIDController pidController;
    private Debouncer debouncer;
    private DigitalInput digitalInput;

    public NeoLifter() {
        digitalInput = new DigitalInput(LifterConstants.SWITCH_ID);
        debouncer = new Debouncer(LifterConstants.DEBOUNCE_TIME_FOR_SWITCH);
        motor = new GBSparkMax(LifterConstants.MOTOR_ID, LifterConstants.MOTOR_TYPE);
        pidController = LifterConstants.PID;
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
    public void goToPosition(double pos) {
        setPower(pidController.calculate(motor.getEncoder().getPosition(), pos));
    }

    @Override
    public void updateInputs(LifterInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.position = motor.getEncoder().getPosition();
        inputs.velocity = motor.getEncoder().getVelocity();
        inputs.isSwitchPressed = debouncer.calculate(digitalInput.get());
        inputs.isMotorAtPosition = Math.abs(motor.getEncoder().getPosition() - inputs.destination) <= LifterConstants.TOLERANCE;
    }
}
