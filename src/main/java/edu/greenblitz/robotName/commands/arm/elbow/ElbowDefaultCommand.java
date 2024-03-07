package edu.greenblitz.robotName.commands.arm.elbow;

public class ElbowDefaultCommand extends ElbowCommand {
	
	@Override
	public void initialize() {
		elbow.setCurrentAngle();
	}
	
	@Override
	public void execute() {
		elbow.standInPlace();
	}
}