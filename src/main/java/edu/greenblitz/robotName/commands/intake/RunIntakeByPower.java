package edu.greenblitz.robotName.commands.intake;

public class RunIntakeByPower extends IntakeCommand{

	private double power;

	public RunIntakeByPower(double power){
		super();
		this.power = power;
	}

	@Override
	public void execute() {
		intake.setPower(power);
	}

	@Override
	public void end(boolean interrupted) {
		intake.stop();
	}
}
