package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import java.util.function.BooleanSupplier;

public class MovePivotToAngle extends PivotCommand {

	private double targetAngle;

	public MovePivotToAngle(double targetAngle, boolean isSimulation) {
		this.targetAngle = targetAngle;
	}

	@Override
	public void initialize() {
		pivot.moveToAngle(targetAngle);
	}

	@Override
	public void execute() {
		if (Robot.isSimulation())
			pivot.moveToAngle(targetAngle);
	}

	public boolean isFinished() {
		return pivot.isAtAngle(targetAngle);
	}
	
}
