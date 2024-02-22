package edu.greenblitz.robotName.commands.LED;

import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.LED.LEDConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.Timer;


public class UpdateLEDStateDefaultCommand extends GBCommand {
	
	private LED led;
	private Timer actionTimer;

	public UpdateLEDStateDefaultCommand() {
		led = LED.getInstance();
		require(led);
		actionTimer = new Timer();
	}

	@Override
	public void execute() {
		if (led.getWasNoteInRobot() && !led.isNoteInRobot()) {
			actionTimer.restart();
		}
		else if (!led.getWasNoteInRobot() && led.isNoteInRobot()) {
			actionTimer.restart();
		}
		if (led.isNoteInRobot() && actionTimer.get() <= LEDConstants.BLINKING_TIME) {
			led.blink(led.getColorByMode());
		} else if (actionTimer.get() <= LEDConstants.RUMBLE_TIME){
			led.rumble();
		}
		else {
			led.stopRumble();
		}
		if (!led.isNoteInRobot()) {
			led.setColorByMode();
		}
		led.updateNoteState();
		
	}
}
