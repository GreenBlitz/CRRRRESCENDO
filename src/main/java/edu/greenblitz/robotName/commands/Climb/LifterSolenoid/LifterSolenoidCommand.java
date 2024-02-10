package edu.greenblitz.robotName.commands.Climb.LifterSolenoid;

import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoid;
import edu.greenblitz.robotName.utils.GBCommand;

public abstract class LifterSolenoidCommand extends GBCommand {

	protected LifterSolenoid lifterSolenoid;

	public LifterSolenoidCommand() {
		lifterSolenoid = LifterSolenoid.getInstance();
		require(lifterSolenoid);
	}
}
