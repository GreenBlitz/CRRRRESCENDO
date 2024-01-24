package edu.greenblitz.robotName.commands.shooter.pivot.movePivotToAngle;

import edu.greenblitz.robotName.commands.shooter.pivot.PivotCommand;

public class MotorMovePivotToAngle extends PivotCommand {
	
	private double goalAngle;
	
	public MotorMovePivotToAngle(double goalAngle){
		super();
		this.goalAngle = goalAngle;
	}
	
	@Override
	public void initialize() {
		pivot.moveToAngle(goalAngle);
	}
	
	@Override
	public boolean isFinished() {
		return pivot.isAtAngle(goalAngle);
	}
}
