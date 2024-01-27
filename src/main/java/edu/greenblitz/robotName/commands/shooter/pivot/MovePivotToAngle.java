package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.Robot;
import edu.wpi.first.math.geometry.Rotation2d;

public class MovePivotToAngle extends PivotCommand {

	private Rotation2d targetAngle;

	public MovePivotToAngle(Rotation2d targetAngle) {
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
