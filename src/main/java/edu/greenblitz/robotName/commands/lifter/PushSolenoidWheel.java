package edu.greenblitz.robotName.commands.lifter;

import edu.wpi.first.wpilibj.Timer;

public class PushSolenoidWheel extends LifterCommand{
	
	private Timer timer;
	
	public PushSolenoidWheel(){
		timer = new Timer();
	}
	
	@Override
	public void initialize() {
		timer.restart();
		lifter.setPower(0.2);
	}
	
	@Override
	public boolean isFinished() {
		return timer.hasElapsed(0.2);
	}
	
	@Override
	public void end(boolean interrupted) {
		lifter.stop();
	}
}
