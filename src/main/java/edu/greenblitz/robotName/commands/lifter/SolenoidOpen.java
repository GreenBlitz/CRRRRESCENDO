package edu.greenblitz.robotName.commands.lifter;


import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;
import edu.wpi.first.wpilibj.Timer;

public class SolenoidOpen extends LifterCommand{
	private Timer timer;
	
	public SolenoidOpen(){
		timer = new Timer();
	}
	
	@Override
	public void initialize() {
		timer.restart();
	}
	
	@Override
	public void execute() {
		lifter.openSolenoid();
	}
	
	@Override
	public boolean isFinished() {
		return timer.hasElapsed(LifterConstants.SECONDS_TO_CLOSE_SOLENOID);
	}
}
