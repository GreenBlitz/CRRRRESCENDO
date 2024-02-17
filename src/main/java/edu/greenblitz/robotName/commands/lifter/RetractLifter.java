package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;

public class RetractLifter extends LifterCommand {

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
        lifter.stop();
    }
}