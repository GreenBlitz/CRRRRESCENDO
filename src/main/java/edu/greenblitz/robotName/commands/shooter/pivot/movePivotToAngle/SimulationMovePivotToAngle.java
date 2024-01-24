package edu.greenblitz.robotName.commands.shooter.pivot.movePivotToAngle;

import edu.greenblitz.robotName.commands.shooter.pivot.PivotCommand;

public class SimulationMovePivotToAngle extends PivotCommand {
	
	private double targetAngle;
	
	public SimulationMovePivotToAngle(double targetAngle){
		super();
		this.targetAngle = targetAngle;
	}
	
	@Override
	public void execute() {
		pivot.moveToAngle(targetAngle);
	}
	
	@Override
	public boolean isFinished() {
		return pivot.isAtAngle(targetAngle);
	}
}
