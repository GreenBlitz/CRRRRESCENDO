package edu.greenblitz.robotName.commands.Pivot;

import edu.greenblitz.robotName.subsystems.Pivot.Pivot;
import edu.greenblitz.robotName.utils.GBCommand;

public class PivotCommand extends GBCommand {

	protected Pivot pivot;

	public PivotCommand(){
		pivot = Pivot.getInstance();
		require(pivot);
	}

	@Override
	public boolean isFinished() {
		return pivot.isAtGoalAngle();
	}
	@Override
	public void end(boolean interrupted) {
		if (interrupted)
			pivot.setGoalAngle(pivot.getAngleInRadians());
	}

}
