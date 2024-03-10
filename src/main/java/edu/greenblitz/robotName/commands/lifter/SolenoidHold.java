package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;
import edu.wpi.first.wpilibj.Timer;

public class SolenoidHold extends LifterCommand {

    private Timer timer;
    
    public SolenoidHold(){
        timer = new Timer();
    }
    
    @Override
    public void initialize() {
        timer.restart();
    }
    
    @Override
    public void execute() {
        lifter.holdSolenoid();
    }
    
    @Override
    public boolean isFinished() {
        return timer.hasElapsed(LifterConstants.SECONDS_TO_HOLD_SOLENOID);
    }
}
