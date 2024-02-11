package edu.greenblitz.robotName.commands.Climb.lifter;

import edu.greenblitz.robotName.subsystems.Lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoidConstants;

public class ExtendLifter extends LifterCommand {

    @Override
    public void initialize() {
        lifterSolenoid.closeSolenoid();
        timer.start();
    }

    @Override
    public void execute() {
        if (timer.hasElapsed(LifterSolenoidConstants.SECONDS_TO_CLOSE)){
            lifterSolenoid.holdSolenoid();
            lifter.goToPosition(LifterConstants.LIFTER_EXTENDED_POSITION);
        }
    }

    @Override
    public boolean isFinished() {
        return lifter.isAtPosition(LifterConstants.LIFTER_EXTENDED_POSITION);
    }

    @Override
    public void end(boolean interrupted) {
        lifter.stopMotor();
        lifterSolenoid.openSolenoid();
    }
}
