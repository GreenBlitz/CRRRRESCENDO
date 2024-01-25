package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.Robot;

public class MovePivotToAngle extends PivotCommand {

	private double targetAngle;

	public MovePivotToAngle(double targetAngle) {
		this.targetAngle = targetAngle;
	}

	@Override
	public void initialize() {
		pivot.moveToAngle(targetAngle);
	}

	@Override
	public void execute() {
		if (Robot.isSimulation()) {
			pivot.moveToAngle(targetAngle);
		}
	}

	public boolean isFinished() {
		return pivot.isAtAngle(targetAngle);
	}
	
}
