package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.Robot;
import edu.wpi.first.math.geometry.Rotation2d;

import java.util.function.Supplier;

public class MovePivotToAngle extends PivotCommand {

	private Supplier<Rotation2d> targetAngle;

	private Rotation2d supplliedTargetAngle;

	public MovePivotToAngle(Rotation2d targetAngle) {
		this.targetAngle = () -> targetAngle;
	}

	public MovePivotToAngle(Supplier<Rotation2d> targetAngle) {
		this.targetAngle = targetAngle;
	}

	@Override
	public void initialize() {
		supplliedTargetAngle = targetAngle.get();
		System.out.println(supplliedTargetAngle);
		pivot.moveToAngle(supplliedTargetAngle);
	}

	@Override
	public void execute() {
		System.out.println(pivot.getAngle()+","+supplliedTargetAngle);
		if (Robot.isSimulation()) {
			pivot.moveToAngle(supplliedTargetAngle);
		}
	}

	public boolean isFinished() {
		return pivot.isAtAngle(supplliedTargetAngle);
	}

}
