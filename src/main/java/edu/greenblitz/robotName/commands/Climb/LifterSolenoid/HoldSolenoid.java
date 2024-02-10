package edu.greenblitz.robotName.commands.Climb.LifterSolenoid;

import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoidConstants;

public class HoldSolenoid extends LifterSolenoidCommand{

	@Override
	public void initialize() {
		lifterSolenoid.setPower(LifterSolenoidConstants.POWER_TO_HOLD);
	}

	@Override
	public void end(boolean interrupted) {
		lifterSolenoid.setPower(0);
	}
}
