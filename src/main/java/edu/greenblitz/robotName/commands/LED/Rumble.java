package edu.greenblitz.robotName.commands.LED;

import edu.greenblitz.robotName.OI;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.Timer;

public class Rumble extends GBCommand {
	
	Timer timer;
	
	public Rumble(){
		timer = new Timer();
	}
	
	@Override
	public void initialize() {
		timer.start();
	}
	
	@Override
	public void execute() {
		OI.getInstance().getMainJoystick().rumble(true, 0.5);
	}
	
	@Override
	public boolean isFinished() {
		return timer.get()>=5;
	}
	
	@Override
	public void end(boolean interrupted) {
		OI.getInstance().getMainJoystick().rumble(true, 0);
		timer.reset();
	}
}
