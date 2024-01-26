package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.subsystems.Lifter.NeoLifter.NeoLifterConstants;

public class ReverseLifting extends LifterCommand {
    @Override
    public void execute() {
        lifter.goToPosition(NeoLifterConstants.LIFTER_RETRACTED_POSITION);
    }

    @Override
    public boolean isFinished() {
        return lifter.isMotorAtPosition(NeoLifterConstants.LIFTER_RETRACTED_POSITION);
    }

    @Override
    public void end(boolean interrupted) {
        lifter.stopMotor();
    }
}
