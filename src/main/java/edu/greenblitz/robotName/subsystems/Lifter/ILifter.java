package edu.greenblitz.robotName.subsystems.Lifter;

public interface ILifter {

    public void setPower(double power);

    public void setVoltage(double voltage);

    public void setPosition(double position);

    public boolean isMotorInPosition(double position);

    public void updateInputs(LifterInputs lastInputs);

}

