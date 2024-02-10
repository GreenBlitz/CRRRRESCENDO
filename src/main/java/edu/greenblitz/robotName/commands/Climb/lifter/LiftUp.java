package edu.greenblitz.robotName.commands.Climb.lifter;

import edu.greenblitz.robotName.commands.Climb.LifterSolenoid.CloseThenHoldSolenoid;
import edu.greenblitz.robotName.subsystems.Lifter.LifterConstants;

public class LiftUp extends LifterCommand {

    @Override
    public void initialize() {
        new CloseThenHoldSolenoid();
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
