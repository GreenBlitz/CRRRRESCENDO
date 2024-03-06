package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;
import edu.wpi.first.wpilibj.Timer;

public class SolenoidClose extends LifterCommand{
	 private Timer timer;
	 
	 public SolenoidClose(){
		 timer = new Timer();
	 }
	
	@Override
	public void initialize() {
		 timer.restart();

	}
	
	@Override
	public void execute() {
		lifter.closeSolenoid();
	}
	
	@Override
	public boolean isFinished() {
		return timer.hasElapsed(LifterConstants.SECONDS_TO_CLOSE_SOLENOID);
	}
}
