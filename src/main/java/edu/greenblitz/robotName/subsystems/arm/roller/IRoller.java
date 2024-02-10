package edu.greenblitz.robotName.subsystems.arm.roller;


public interface IRoller {
	
	void setPower(double power);
	
	void updateInputs(RollerInputsAutoLogged rollerInputs);

}
