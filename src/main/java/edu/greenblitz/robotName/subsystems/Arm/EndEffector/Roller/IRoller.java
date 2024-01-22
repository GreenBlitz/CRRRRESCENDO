package edu.greenblitz.robotName.subsystems.Arm.EndEffector.Roller;

public interface IRoller {
	
	void setPower(double power);
	
	void updateInputs(RollerInputsAutoLogged rollerInputs);
}
