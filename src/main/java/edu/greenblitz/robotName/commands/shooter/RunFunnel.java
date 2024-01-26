package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;

public class RunFunnel extends FunnelCommand {

    @Override
    public void execute() {
        funnel.setPower(FunnelConstants.ROLL_POWER);
    }

    @Override
    public boolean isFinished() {
        return !funnel.isObjectIn();
    }
}
