package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;

public class LiftUp extends LifterCommand {

    @Override
    public void initialize() {
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
        lifter.stopMotor();
    }
}