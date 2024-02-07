package edu.greenblitz.robotName.commands.shooter.funnel;

public class FunnelDefaultCommand extends FunnelCommand{

    @Override
    public void initialize() {
        funnel.stop();
    }

}
