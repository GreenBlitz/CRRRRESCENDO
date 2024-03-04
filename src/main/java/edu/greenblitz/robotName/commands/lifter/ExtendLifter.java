package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;
import edu.wpi.first.wpilibj.Timer;

public class ExtendLifter extends LifterCommand {

    protected Timer timer;

    public ExtendLifter() {
        timer = new Timer();
    }

    @Override
    public void initialize() {
        lifter.goToPosition(LifterConstants.LIFTER_EXTENDED_POSITION);
    }

    @Override
    public void execute() {
        lifter.closeSolenoid();
    }

    @Override
    public boolean isFinished() {
        return lifter.isAtPosition(LifterConstants.LIFTER_EXTENDED_POSITION);
    }

    @Override
    public void end(boolean interrupted) {
        lifter.stop();
        lifter.openSolenoid();
        timer.stop();
        timer.reset();
    }
}