package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.subsystems.Lifter.NeoLifter.NeoLifterConstants;

public class LiftUp extends LifterCommand {

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        lifter.goToPosition(NeoLifterConstants.LIFTER_EXTENDED_POSITION);
    }

    @Override
    public boolean isFinished() {
        return lifter.isMotorAtPosition(NeoLifterConstants.LIFTER_EXTENDED_POSITION);
    }

    @Override
    public void end(boolean interrupted) {
        lifter.stopMotor();
    }
}
