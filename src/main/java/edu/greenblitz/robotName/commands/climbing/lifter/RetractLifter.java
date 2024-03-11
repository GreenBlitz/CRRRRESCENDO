package edu.greenblitz.robotName.commands.climbing.lifter;

import edu.greenblitz.robotName.subsystems.climbing.lifter.LifterConstants;

public class RetractLifter extends LifterCommand {

    @Override
    public void initialize() {
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