package edu.greenblitz.robotName.commands.shooter.funnel;

public class StopFunnel extends FunnelCommand {
	@Override
	public void initialize() {
		funnel.stop();
	}
}