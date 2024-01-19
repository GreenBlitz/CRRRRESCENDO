package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxPIDController;
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
    public void setPowerForMotor1(double power) {
        motor1.set(power);
    }
    @Override
    public void setPowerForMotor2(double power) {
        motor2.set(power);
    }
    @Override
    public void setVoltageForMotor1(double voltage) {
        motor1.setVoltage(voltage);
    }
    @Override
    public void setVoltageForMotor2(double voltage) {
        motor2.setVoltage(voltage);
    }

    @Override
    public void setPositionForMotor1(double position) {
        motor1.getEncoder().setPosition(position);
    }
    @Override
    public void setPositionForMotor2(double position) {
        motor2.getEncoder().setPosition(position);
    }

    @Override
    public boolean isMotor1InPosition(double position) {
        return motor1.getEncoder().getPosition() <= position + LifterConstants.PID_TOLERANCE || motor1.getEncoder().getPosition() >= position - LifterConstants.PID_TOLERANCE;
    }
    @Override
    public boolean isMotor2InPosition(double position) {
        return motor2.getEncoder().getPosition() <= position + LifterConstants.PID_TOLERANCE || motor2.getEncoder().getPosition() >= position - LifterConstants.PID_TOLERANCE;
    }

    @Override
    public void updateInputs(LifterInputs lastInputs) {

    }
    @Override
    public void stopMotor1() {
        this.setPowerForMotor1(0);
    }
    @Override
    public void stopMotor2() {
        this.setPowerForMotor2(0);
    }
    @Override
    public void setIdleModeForMotor1(CANSparkMax.IdleMode mode) {
        motor1.setIdleMode(mode);
    }
    @Override
    public void setIdleModeForMotor2(CANSparkMax.IdleMode mode) {
        motor2.setIdleMode(mode);
    }

    @Override
    public SparkMaxPIDController getMotor1PID() {
        return motor1.getPIDController();
    }

    @Override
    public SparkMaxPIDController getMotor2PID() {
        return motor2.getPIDController();
    }
}
