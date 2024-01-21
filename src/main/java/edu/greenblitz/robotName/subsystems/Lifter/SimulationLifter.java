package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;

public class SimulationLifter implements ILifter{

    private LifterInputs lastInputsForMotor1 = new LifterInputs();
    private LifterInputs lastInputsForMotor2 = new LifterInputs();

    @Override
    public void setPowerForMotor1(double power) {
        lastInputsForMotor1.power = power;
    }

    @Override
    public void setPowerForMotor2(double power) {
        lastInputsForMotor2.power = power;
    }


    @Override
    public void setVoltageForMotor1(double voltage) {
        lastInputsForMotor1.voltage = voltage;
    }
    @Override
    public void setVoltageForMotor2(double voltage) {
        lastInputsForMotor2.voltage = voltage;
    }

    @Override
    public void setPositionForMotor1(double position) {
        lastInputsForMotor1.position = position;
    }
    @Override
    public void setPositionForMotor2(double position) {
        lastInputsForMotor2.position = position;
    }

    @Override
    public boolean isMotor1InPosition(double position) {
        return lastInputsForMotor1.position <= position + LifterConstants.PID_TOLERANCE &&  lastInputsForMotor1.position >= position - LifterConstants.PID_TOLERANCE;
    }
    @Override
    public boolean isMotor2InPosition(double position) {
        return lastInputsForMotor2.position <= position + LifterConstants.PID_TOLERANCE &&  lastInputsForMotor2.position >= position - LifterConstants.PID_TOLERANCE;
    }

    @Override
    public void updateInputs(LifterInputs lastInputs) {

    }
    @Override
    public void stopMotor1() {
        lastInputsForMotor1.power = 0;
    }
    @Override
    public void stopMotor2() {
        lastInputsForMotor2.power = 0;
    }

    @Override
    public void setIdleModeForMotor1(CANSparkMax.IdleMode mode) {
        lastInputsForMotor1.idleMode = mode;
    }
    @Override
    public void setIdleModeForMotor2(CANSparkMax.IdleMode mode) {
        lastInputsForMotor2.idleMode = mode;
    }

    @Override
    public double getPositionForMotor1() {
        return lastInputsForMotor1.position;
    }

    @Override
    public double getPositionForMotor2() {
        return lastInputsForMotor2.position;
    }

    @Override
    public boolean isSwitchPressed() {
        return lastInputsForMotor1.position <= 0;
    }

}
