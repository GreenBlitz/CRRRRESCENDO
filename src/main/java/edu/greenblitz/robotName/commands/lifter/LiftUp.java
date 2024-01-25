package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.subsystems.Lifter.LifterConstants;

public class LiftUp extends LifterCommand {

    @Override
    public void initialize() {
        lifter.setDestination(LifterConstants.LIFTER_EXTENDED_POSITION);
    }

    @Override
    public void execute() {
        lifter.goToDestinationByPID();
    }

    @Override
    public boolean isFinished() {
        return lifter.isMotorAtDestination();
    }

    @Override
    public void end(boolean interrupted) {
        lifter.stopMotor();
    }
}
