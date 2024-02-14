package edu.greenblitz.robotName.commands.intake;

public class RollIntakeByPower extends IntakeCommand{

	private double power;

	public RollIntakeByPower(double power) {
		this.power = power;
	}

	@Override
	public void execute() {
		intake.setPower(power);
	}

	public void end(boolean interrupted) {
		intake.stop();
	}

}
