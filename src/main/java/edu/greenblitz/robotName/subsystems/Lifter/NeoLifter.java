package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.PIDController;

public class NeoLifter implements ILifter {

    private GBSparkMax motor1;
    private GBSparkMax motor2;
    private PIDController pid1;
    private PIDController pid2;

    public NeoLifter() {

        motor1 = new GBSparkMax(LifterConstants.MOTOR_PORT_ID1, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor2 = new GBSparkMax(LifterConstants.MOTOR_PORT_ID2, CANSparkMaxLowLevel.MotorType.kBrushless);

        pid1 = new PIDController(LifterConstants.PID_KP,LifterConstants.PID_KI,LifterConstants.PID_KD);
        pid1.setTolerance(LifterConstants.PID_TOLERANCE);

        pid2 = new PIDController(LifterConstants.PID_KP,LifterConstants.PID_KI,LifterConstants.PID_KD);
        pid2.setTolerance(LifterConstants.PID_TOLERANCE);

    }
    @Override
    public void moveByCalculatedDistance(double distance) {
        motor1.set(pid1.calculate(motor1.getEncoder().getPosition(),distance));
        motor2.set(pid2.calculate(motor2.getEncoder().getPosition(),distance));
    }
    @Override
    public void setPower(double power) {
        motor1.set(power);
        motor2.set(power);
    }

    @Override
    public void setVoltage(double voltage) {
        motor1.setVoltage(voltage);
        motor2.setVoltage(voltage);
    }

    @Override
    public void setPosition(double position) {
        motor1.getEncoder().setPosition(position);
        motor2.getEncoder().setPosition(position);
    }

    @Override
    public boolean isMotorInPosition(double position) {
        return motor1.getEncoder().getPosition() <= position + LifterConstants.PID_TOLERANCE || motor1.getEncoder().getPosition() >= position - LifterConstants.PID_TOLERANCE;
    }

    @Override
    public void updateInputs(LifterInputs lastInputs) {

    }
    @Override
    public void stopMotor() {
        this.setPower(0);
    }
    @Override
    public void setIdleMode(CANSparkMax.IdleMode mode) {
        motor1.setIdleMode(mode);
        motor2.setIdleMode(mode);
    }
}
