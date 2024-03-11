package edu.greenblitz.robotName.commands.climbing.lifter;

import edu.greenblitz.robotName.subsystems.climbing.lifter.LifterConstants;

public class ExtendLifter extends LifterCommand {

    @Override
    public void initialize() {
        lifter.goToPosition(LifterConstants.LIFTER_EXTENDED_POSITION);
    }
    
    @Override
    public boolean isFinished() {
        return lifter.isAtPosition(LifterConstants.LIFTER_EXTENDED_POSITION);
    }

    @Override
    public void end(boolean interrupted) {
        lifter.stop();
    }
}