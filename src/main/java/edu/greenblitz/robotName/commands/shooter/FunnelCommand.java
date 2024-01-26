package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.utils.GBCommand;

public abstract class FunnelCommand extends GBCommand {

    protected Funnel funnel;

    public FunnelCommand() {
        funnel = Funnel.getInstance();
        require(funnel);
    }

    @Override
    public boolean isFinished() {
        return !Funnel.getInstance().isObjectIn();
    }
}
