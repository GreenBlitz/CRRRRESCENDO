package edu.greenblitz.robotName.subsystems.ChassisIntake;

public interface IChassisIntake {
	void setPower(double power);
	
	void setVoltage(double voltage);
	
	void updateInputs(ChassisIntakeInputsAutoLogged chassisIntakeInputs);
	
	
}
