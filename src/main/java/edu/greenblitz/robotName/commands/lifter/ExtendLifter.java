package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.subsystems.Lifter.LifterConstants;
import edu.wpi.first.wpilibj.Timer;

public class ExtendLifter extends LifterCommand {

    protected Timer timer;

    public ExtendLifter(){
        timer = new Timer();
    }

    @Override
    public void initialize() {
        lifter.closeSolenoid();
        timer.start();
    }

    @Override
    public void execute() {
        if (timer.hasElapsed(LifterConstants.SECONDS_TO_CLOSE_SOLENOID)){
            lifter.holdSolenoid();
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
        lifter.openSolenoid();
        timer.stop();
        timer.reset();
    }
}
