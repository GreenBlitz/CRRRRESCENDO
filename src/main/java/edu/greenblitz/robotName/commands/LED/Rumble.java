package edu.greenblitz.robotName.commands.LED;

import edu.greenblitz.robotName.OI;
import edu.greenblitz.robotName.subsystems.LED.LEDConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.Timer;

public class Rumble extends GBCommand {
	private Timer timer;
	
	public Rumble() {
		timer = new Timer();
	}
	
	@Override
	public void initialize() {
		timer.restart();
	}
	
	@Override
	public void execute() {
		OI.getInstance().getMainJoystick().rumble(LEDConstants.RUMBLE_LEFT_MOTOR, LEDConstants.RUMBLE_POWER);
	}
	
	@Override
	public boolean isFinished() {
		return timer.get() >= LEDConstants.RUMBLE_TIME;
	}
	
	@Override
	public void end(boolean interrupted) {
		OI.getInstance().getMainJoystick().rumble(LEDConstants.RUMBLE_LEFT_MOTOR, 0);
	}
}
