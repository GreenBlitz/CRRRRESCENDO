package edu.greenblitz.robotName.commands.Arm;

import edu.greenblitz.robotName.subsystems.Arm.Arm;
import edu.greenblitz.robotName.subsystems.Arm.Elbow.Elbow;
import edu.greenblitz.robotName.subsystems.Arm.Pivot.Pivot;
import edu.greenblitz.robotName.utils.GBCommand;

public class ArmCommand extends GBCommand {

	protected Arm arm;

	public ArmCommand(){
		arm = Arm.getInstance();
		require(arm.getElbow());
		require(arm.getPivot());
	}

	@Override
	public boolean isFinished() {
		return arm.isAtGoalAngles();
	}
}
