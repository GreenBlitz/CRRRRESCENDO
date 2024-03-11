package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.subsystems.climber.lifter.Lifter;
import edu.greenblitz.robotName.subsystems.climber.lifter.LifterConstants;

public class CheckLiftUp extends SystemCheckCommand {

    protected Lifter lifter;

    public CheckLiftUp() {
        lifter = Lifter.getInstance();
        require(lifter);
    }

    @Override
    public void execute() {
        lifter.goToPosition(LifterConstants.LIFTER_EXTENDED_POSITION);
    }

    @Override
    public boolean isFinished() {
        return lifter.isAtPosition(LifterConstants.LIFTER_EXTENDED_POSITION);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        lifter.stop();
    }
}
