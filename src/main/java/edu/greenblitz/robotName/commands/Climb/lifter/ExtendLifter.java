package edu.greenblitz.robotName.commands.Climb.lifter;

import edu.greenblitz.robotName.subsystems.Lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoidConstants;
import edu.wpi.first.wpilibj.Timer;

public class ExtendLifter extends LifterCommand {
    
    protected Timer timer;

    public ExtendLifter(){
        timer = new Timer();
    }
    
    @Override
    public void initialize() {
        lifterSolenoid.closeSolenoid();
        timer.start();
    }

    @Override
    public void execute() {
        if (timer.hasElapsed(LifterSolenoidConstants.SECONDS_TO_CLOSE)){
            lifterSolenoid.holdSolenoid();
//            lifter.goToPosition(LifterConstants.LIFTER_EXTENDED_POSITION);
            lifter.setPower(0.1);
        }
    }

    @Override
    public boolean isFinished() {
//        return lifter.isAtPosition(LifterConstants.LIFTER_EXTENDED_POSITION);
//        return timer.hasElapsed(3);
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        lifter.stopMotor();
        lifterSolenoid.openSolenoid();
        timer.stop();
        timer.reset();
    }
}
