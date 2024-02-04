package edu.greenblitz.robotName.commands.LED;

import edu.greenblitz.robotName.OI;
import edu.greenblitz.robotName.subsystems.LED.LEDConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.wpilibj.Timer;

public class Rumble extends GBCommand {
	
	private Timer timer;
	private SmartJoystick mainJoystick;
	public void rumble(){
		mainJoystick = OI.getInstance().getMainJoystick();
		timer = new Timer();
	}
	
	@Override
	public void initialize() {
		timer.start();
	}
	
	@Override
	public void execute() {
		mainJoystick.rumble(LEDConstants.RUMBLE_LEFT_MOTOR, LEDConstants.RUMBLE_POWER);
	}
	
	@Override
	public boolean isFinished() {
		return timer.get() >= LEDConstants.BLINKING_TIME;
	}
	
	@Override
	public void end(boolean interrupted) {
		mainJoystick.rumble(LEDConstants.RUMBLE_LEFT_MOTOR, 0);
		timer.reset();
	}
}