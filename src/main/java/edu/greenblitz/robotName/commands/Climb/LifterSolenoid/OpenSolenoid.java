package edu.greenblitz.robotName.commands.Climb.LifterSolenoid;

import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoidConstants;

public class OpenSolenoid extends LifterSolenoidCommand{

	@Override
	public void initialize() {
		lifterSolenoid.setPower(LifterSolenoidConstants.POWER_TO_OPEN);
	}
}
