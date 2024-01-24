package edu.greenblitz.robotName.commands.shooter.pivot.movePivotToAngle;

import edu.greenblitz.robotName.commands.shooter.pivot.PivotCommand;

public class MotorMovePivotToAngle extends PivotCommand {
	
	private double targetAngle;
	
	public MotorMovePivotToAngle(double targetAngle){
		super();
		this.targetAngle = targetAngle;
	}
	
	@Override
	public void initialize() {
		pivot.moveToAngle(targetAngle);
	}
	
	@Override
	public boolean isFinished() {
		return pivot.isAtAngle(targetAngle);
	}
}
