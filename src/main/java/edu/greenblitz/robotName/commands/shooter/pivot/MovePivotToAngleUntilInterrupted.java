package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.math.geometry.Rotation2d;

import java.util.function.Supplier;

public class MovePivotToAngleUntilInterrupted extends PivotCommand{

	private Supplier<Rotation2d> targetAngle;

	private Rotation2d suppliedTargetAngle;

	public MovePivotToAngleUntilInterrupted(PivotConstants.PresetPositions targetAngle) {
		this.targetAngle = () -> targetAngle.ANGLE;
	}

	public MovePivotToAngleUntilInterrupted(Rotation2d targetAngle) {
		this.targetAngle = () -> targetAngle;
	}

	public MovePivotToAngleUntilInterrupted(Supplier<Rotation2d> targetAngle) {
		this.targetAngle = targetAngle;
	}

	@Override
	public void initialize() {
		super.initialize();
		suppliedTargetAngle = targetAngle.get();
		pivot.moveToAngle(suppliedTargetAngle);
	}

	@Override
	public void execute() {
		if (Robot.isSimulation()) {
			pivot.moveToAngle(suppliedTargetAngle);
		}
	}

}
