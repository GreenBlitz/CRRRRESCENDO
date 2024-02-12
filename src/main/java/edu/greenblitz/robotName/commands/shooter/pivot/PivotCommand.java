package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.utils.GBCommand;

public class PivotCommand extends GBCommand {
	
	protected Pivot pivot;
	
	public PivotCommand(){
		pivot = Pivot.getInstance();
		require(pivot);
	}

	public void end(boolean interrupted) {
		pivot.setCurrentAngle();
		pivot.standInPlace();
	}
}
