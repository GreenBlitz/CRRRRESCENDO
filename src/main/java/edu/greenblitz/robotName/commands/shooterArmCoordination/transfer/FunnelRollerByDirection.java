package edu.greenblitz.robotName.commands.shooterArmCoordination.transfer;

import edu.greenblitz.robotName.subsystems.arm.Roller;
import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.utils.GBCommand;

public class FunnelRollerByDirection extends GBCommand {

    private Roller roller;

    private Funnel funnel;

    private boolean isFunnelToRoller;

    protected FunnelRollerByDirection(boolean isFunnelToRoller) {
        this.isFunnelToRoller = isFunnelToRoller;

        roller = Roller.getInstance();
        funnel = Funnel.getInstance();

        require(roller);
        require(funnel);
    }

    @Override
    public void execute() {
        if (isFunnelToRoller) {
            funnel.rollOut();
            roller.rollBackward();
        }
        else {
            funnel.rollIn();
            roller.rollForward();
        }
    }

    @Override
    public boolean isFinished() {
        if (isFunnelToRoller) {
            return !funnel.isObjectIn() && Wrist.getInstance().isObjectInside();
        }
        return funnel.isObjectIn() && !Wrist.getInstance().isObjectInside();
    }

    @Override
    public void end(boolean interrupted) {
        funnel.stop();
        roller.stop();
    }
}
