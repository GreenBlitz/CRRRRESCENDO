package edu.greenblitz.robotName.subsystems.Lifter;

public class SimulationLifter implements ILifter{

    private LifterInputs lastInputs = new LifterInputs();

    @Override
    public void setPower(double power) {
        lastInputs.power = power;
    }

    @Override
    public void setVoltage(double voltage) {
        lastInputs.voltage = voltage;
    }

    @Override
    public void setPosition(double position) {
        lastInputs.position = position;
    }

    @Override
    public boolean isMotorInPosition(double position) {
        return lastInputs.position == position;
    }

    @Override
    public void updateInputs(LifterInputs lastInputs) {

    }
}
