package edu.greenblitz.robotName.commands.climbing.solenoid;

import edu.greenblitz.robotName.subsystems.climbing.solenoid.Solenoid;
import edu.greenblitz.robotName.utils.GBCommand;

public class SolenoidCommand extends GBCommand {

	protected Solenoid solenoid;

	public SolenoidCommand(){
		solenoid = Solenoid.getInstance();
		require(solenoid);
	}
}
