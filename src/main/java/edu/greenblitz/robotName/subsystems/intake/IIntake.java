package edu.greenblitz.robotName.subsystems.intake;

public interface IIntake {
	
	void setPower(double power);
	
	void setVoltage(double voltage);
	
	void setVelocity(double velocity);
	
	void updateInputs(IntakeInputsAutoLogged chassisIntakeInputs);
}