package edu.greenblitz.robotName.commands.LED;

import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.LED.LEDConstants;
import edu.greenblitz.robotName.utils.GBCommand;

public class BlinkIfInShooter extends GBCommand {
	
	private LED led;
	
	public BlinkIfInShooter() {
		led = LED.getInstance();
		require(led);
	}
	
	@Override
	public void initialize() {
		led.restartTimer();
	}
	
	@Override
	public void execute() {
		led.blinkIfInShooter();
	}
	
	@Override
	public boolean isFinished() {
		return led.getTimerTime() > LEDConstants.BLINKING_TIME;
	}
	
	@Override
	public void end(boolean interrupted) {
		led.setLEDColor(LEDConstants.SHOOTER_MODE_COLOR, LEDConstants.ALL_LED);
	}
}
