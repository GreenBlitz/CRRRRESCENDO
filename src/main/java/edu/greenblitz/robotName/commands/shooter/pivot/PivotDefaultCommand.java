package edu.greenblitz.robotName.commands.shooter.pivot;

public class PivotDefaultCommand extends PivotCommand{
	
	@Override
	public void execute() {
		pivot.standInPlace();
	}
}
