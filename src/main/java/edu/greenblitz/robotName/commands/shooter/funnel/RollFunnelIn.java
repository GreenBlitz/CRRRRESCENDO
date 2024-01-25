package edu.greenblitz.robotName.commands.shooter.funnel;

public class RollFunnelIn extends FunnelCommand{

    @Override
    public void execute() {
        funnel.rollIn();
    }

    @Override
    public boolean isFinished() {
        return funnel.isObjectIn();
    }

    @Override
    public void end(boolean interrupted) {
        funnel.stop();
    }
}
