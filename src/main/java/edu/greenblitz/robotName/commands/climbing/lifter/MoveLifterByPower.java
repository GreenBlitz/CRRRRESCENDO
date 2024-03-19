package edu.greenblitz.robotName.commands.climbing.lifter;

public class MoveLifterByPower extends LifterCommand {

	private double power;

	public MoveLifterByPower(double power) {
		this.power = power;
	}

	@Override
	public void execute() {
		lifter.setPower(power);
	}

	@Override
	public void end(boolean interrupted) {
		lifter.stop();
	}
}
