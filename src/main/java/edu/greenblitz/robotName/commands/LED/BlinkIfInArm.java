package edu.greenblitz.robotName.commands.LED;

import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.LED.LEDConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.Timer;

public class  BlinkIfInArm extends GBCommand {
	private LED led;
	
	public BlinkIfInArm() {
		led = LED.getInstance();
		require(led);
	}
	
	@Override
	public void initialize() {
		led.restartTimer();
	}
	
	@Override
	public void execute() {
		led.blinkIfInArm();
	}
	
	@Override
	public boolean isFinished() {
		return led.getTimerTime() > LEDConstants.BLINKING_TIME;
	}
	
	@Override
	public void end(boolean interrupted) {
		LED.getInstance().setLEDColor(LEDConstants.AMP_MODE_COLOR, LEDConstants.ALL_LED);
	}
}

