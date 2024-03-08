package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.math.geometry.Rotation2d;

import java.util.function.Supplier;

public class MovePivotToAngle extends PivotCommand {
	
	private Supplier<Rotation2d> targetAngle;
	
	private Rotation2d suppliedTargetAngle;
	
	public MovePivotToAngle(PivotConstants.PresetPositions targetAngle) {
		this.targetAngle = () -> targetAngle;
	}
	
	public MovePivotToAngle(Rotation2d targetAngle) {
		this.targetAngle = () -> targetAngle;
	}
	
	public MovePivotToAngle(Supplier<Rotation2d> targetAngle) {
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
	
	@Override
	public boolean isFinished() {
		return pivot.isAtAngle(suppliedTargetAngle);
	}
}