package edu.greenblitz.robotName.commands.Climb.lifter;

import edu.greenblitz.robotName.subsystems.Lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoidConstants;

public class ReverseLifting extends LifterCommand {

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
        lifter.stopMotor();
    }
}
