package edu.greenblitz.robotName.subsystems.Intake;

public interface IGripper {

    void setPower(double power);

    void setVoltage(double voltage);

    void updateInputs(GripperInputs inputs);


}
