package edu.greenblitz.robotName.commands.lifter;

public class MoveLifterByPower extends LifterCommand{
	
	private double power;
	
	public MoveLifterByPower(double power){
		this.power = power;
	}
	
	@Override
	public void initialize() {
		lifter.setPower(power);
	}
	
	@Override
	public void end(boolean interrupted) {
		super.end(interrupted);
	}
}
