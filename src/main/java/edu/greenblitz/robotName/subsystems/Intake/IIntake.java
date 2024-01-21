package edu.greenblitz.robotName.subsystems.Intake;

public interface IIntake {
	void setPower(double power);
	
	void setVoltage(double voltage);
	
	void updateInputs(IntakeInputsAutoLogged chassisIntakeInputs);
	
	
}
