package edu.greenblitz.robotName.commands.Elbow;

import edu.greenblitz.robotName.subsystems.Elbow.Elbow;
import edu.greenblitz.robotName.utils.GBCommand;

public class ElbowCommand extends GBCommand {

	protected Elbow elbow;

	public ElbowCommand(){
		elbow = Elbow.getInstance();
		require(elbow);
	}

	@Override
	public boolean isFinished() {
		return elbow.isAtGoalAngle();
	}
}
