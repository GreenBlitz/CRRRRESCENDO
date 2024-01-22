package edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils;


public interface IRoller {
	
	void setPower(double power);
	
	void updateInputs(RollerInputsAutoLogged rollerInputs);
}
