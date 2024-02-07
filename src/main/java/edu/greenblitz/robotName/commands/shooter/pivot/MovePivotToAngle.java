package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.Robot;
import edu.wpi.first.math.geometry.Rotation2d;

import java.util.function.Supplier;

public class MovePivotToAngle extends PivotCommand {

	private Supplier<Rotation2d> targetAngle;

	public MovePivotToAngle(Rotation2d targetAngle) {
		this.targetAngle = () -> targetAngle;
	}

	public MovePivotToAngle(Supplier<Rotation2d> targetAngle) {
		this.targetAngle = targetAngle;
	}

	@Override
	public void initialize() {
		pivot.moveToAngle(targetAngle.get());
		System.out.println(targetAngle.get());
	}

	@Override
	public void execute() {
		System.out.println(targetAngle.get());
		if (Robot.isSimulation()) {
			pivot.moveToAngle(targetAngle.get());
		}
	}

	public boolean isFinished() {
		System.out.println(pivot.isAtAngle(targetAngle.get()));
		return pivot.isAtAngle(targetAngle.get());
	}

}
