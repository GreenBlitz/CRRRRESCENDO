package edu.greenblitz.robotName.commands.shooter;

public class StopFunnel extends FunnelCommand {
    @Override
    public void execute() {
        funnel.stop();
    }
}