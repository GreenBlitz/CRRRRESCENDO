package edu.greenblitz.robotName.commands.shooter.funnel;

import edu.greenblitz.robotName.subsystems.arm.Wrist;

public class RollFunnelOut extends FunnelCommand {

    @Override
    public void execute() {
        funnel.rollOut();
    }

    @Override
    public boolean isFinished() {
        return !funnel.isObjectIn() && Wrist.getInstance().isObjectInside();
    }
}
