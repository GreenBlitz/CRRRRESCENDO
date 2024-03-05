package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;
import edu.wpi.first.wpilibj.Timer;

public class PushSolenoidWheel extends LifterCommand{
    protected Timer timer;
    public PushSolenoidWheel(){
        super();
        timer = new Timer();
    }
    @Override
    public void initialize() {
        timer.restart();
        lifter.setPower(LifterConstants.LIFTER_POWER_TO_RELEASES_SOLENOID);
    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(LifterConstants.SECONDS_TO_RELEASE_SOLENOID);
    }

    @Override
    public void end(boolean interrupted) {
        lifter.stop();
    }
}
