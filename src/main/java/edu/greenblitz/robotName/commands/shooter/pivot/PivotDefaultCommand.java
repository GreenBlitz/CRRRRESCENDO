package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.Robot;

public class PivotDefaultCommand extends PivotCommand{
	
	@Override
	public void initialize() {
		pivot.standInPlace();
	}
}
