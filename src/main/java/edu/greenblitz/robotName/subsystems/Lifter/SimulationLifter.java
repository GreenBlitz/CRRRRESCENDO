package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;

public class SimulationLifter implements ILifter{

    private LifterInputs lastInputs = new LifterInputs();

    @Override
    public void setPowerForMotor1(double power) {
        lastInputs.power = power;
    }

    @Override
    public void moveByCalculatedDistance(double distance) {

    }

    @Override
    public void setVoltageForMotor1(double voltage) {
        lastInputs.voltage = voltage;
    }

    @Override
    public void setPositionForMotor1(double position) {
        lastInputs.position = position;
    }

    @Override
    public boolean isMotor1InPosition(double position) {
        return lastInputs.position == position;
    }

    @Override
    public void updateInputs(LifterInputs lastInputs) {


    }

    @Override
    public void stopMotor1() {
        lastInputs.power = 0;
    }

    @Override
    public void setIdleModeForMotor1(CANSparkMax.IdleMode mode) {
        lastInputs.idleMode = mode;
    }

}
