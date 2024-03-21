package edu.greenblitz.robotName.commands.arm.roller.runByPower;

import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.utils.GBCommand;

public class RollCounterClockWiseNoStop extends GBCommand {

	private Roller roller;

	public RollCounterClockWiseNoStop(){
		roller = Roller.getInstance();
		require(roller);
	}

	@Override
	public void execute() {
		roller.rollCounterClockwise();
	}
}
