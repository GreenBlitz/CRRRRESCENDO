package edu.greenblitz.robotName.subsystems.arm.EndEffector.RollerUtils;


public interface IRoller {
	
	void setPower(double power);
	
	void updateInputs(RollerInputsAutoLogged rollerInputs);
}
