package edu.greenblitz.robotName.commands.shooter.pivot.movePivotToAngle;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import java.util.function.BooleanSupplier;

public class MovePivotToAngle extends ConditionalCommand {
	
	public MovePivotToAngle(double goalAngle, boolean isReal) {
		super(new MotorMovePivotToAngle(goalAngle), new SimulationMovePivotToAngle(goalAngle), () -> isReal);
	}
	
}
