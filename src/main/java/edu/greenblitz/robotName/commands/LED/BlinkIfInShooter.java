package edu.greenblitz.robotName.commands.LED;

import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.LED.LEDConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.Timer;

public class BlinkIfInShooter extends GBCommand {
	
	private double endTime;
	private LED led;
	
	public BlinkIfInShooter() {
		endTime = LEDConstants.BLINKING_TIME;
		led = LED.getInstance();
		require(led);
	}
	
	@Override
	public void initialize() {
		led.startTimer();
	}
	
	@Override
	public void execute() {
		led.blinkIfInShooter();
	}
	
	@Override
	public boolean isFinished() {
		return led.getTimerTime() > endTime;
	}
	
	@Override
	public void end(boolean interrupted) {
		led.setLEDColor(LEDConstants.SHOOTER_MODE_COLOR, LEDConstants.ALL_LED);
	}
}
