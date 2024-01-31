package edu.greenblitz.robotName.commands.shooter.funnel;


import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;

public class RunFunnel extends FunnelCommand {

	double power;
	public RunFunnel(double power) {
		this.power = power;
	}

	@Override
	public void execute() {
		funnel.setPower(power);
	}

	@Override
	public void end(boolean interrupted) {
		funnel.stop();
	}
}