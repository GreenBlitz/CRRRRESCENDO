package edu.greenblitz.robotName.commands.LED;

import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.utils.GBCommand;

public class UpdateLEDStateDefaultCommand extends GBCommand {
	
	private LED led;
	
	public UpdateLEDStateDefaultCommand() {
		led = LED.getInstance();
		require(led);
	}
	
	@Override
	public void execute() {
		led.restartTimerByNoteState();
		led.blinkOrRumbleByNoteState();
		led.setColorByNoteState();
		led.updateNoteState();
	}
}