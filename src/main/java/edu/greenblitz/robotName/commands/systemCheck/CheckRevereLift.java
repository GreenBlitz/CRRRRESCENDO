package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.subsystems.climbing.lifter.Lifter;
import edu.greenblitz.robotName.subsystems.climbing.lifter.LifterConstants;

public class CheckRevereLift extends SystemCheckCommand {

    protected Lifter lifter;

    public CheckRevereLift() {
        lifter = Lifter.getInstance();
        require(lifter);
    }

    @Override
    public void execute() {
        lifter.goToPosition(LifterConstants.LIFTER_RETRACTED_POSITION);
    }

    @Override
    public boolean isFinished() {
        return lifter.isSwitchPressed();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        lifter.stop();
    }
}
