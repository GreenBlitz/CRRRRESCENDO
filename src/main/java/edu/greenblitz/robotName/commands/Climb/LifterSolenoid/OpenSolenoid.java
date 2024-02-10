package edu.greenblitz.robotName.commands.Climb.LifterSolenoid;

import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoidConstants;

public class OpenSolenoid extends LifterSolenoidCommand{

	@Override
	public void initialize() {
		lifterSolenoid.setPower(LifterSolenoidConstants.POWER_TO_OPEN);
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public void end(boolean interrupted) {
		lifterSolenoid.setPower(0);
	}
}
