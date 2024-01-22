package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.utils.DigitalInputMap;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class NeoLifter implements ILifter {
    private GBSparkMax motor;
    private LifterInputs inputs;
    public NeoLifter() {
        inputs = new LifterInputs();
        motor = new GBSparkMax(LifterConstants.MOTOR_PORT_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
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
    public void resetEncoderTo(double position) {
        motor.getEncoder().setPosition(position);
        inputs.position = position;
    }

    @Override
    public boolean isMotorInPosition(double position) {
        return motor.getEncoder().getPosition() <= position + LifterConstants.PID_TOLERANCE && motor.getEncoder().getPosition() >= position - LifterConstants.PID_TOLERANCE;
    }

    @Override
    public void updateInputs(LifterInputs inputs) {
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.position = motor.getEncoder().getPosition();
        inputs.velocity = motor.getEncoder().getVelocity();
        inputs.kP = motor.getPIDController().getP();
        inputs.kI = motor.getPIDController().getI();
        inputs.kD = motor.getPIDController().getD();
        inputs.isSwitchPressed = DigitalInputMap.getInstance().getValue(LifterConstants.SWITCH_ID);

    }
    @Override
    public void stopMotor() {
        this.setPower(0);
    }
    @Override
    public void setIdleMode(CANSparkMax.IdleMode mode) {
        motor.setIdleMode(mode);
    }

    @Override
    public boolean isSwitchPressed() {
        return DigitalInputMap.getInstance().getValue(LifterConstants.SWITCH_ID);
    }
}
