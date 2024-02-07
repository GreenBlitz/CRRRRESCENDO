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
		System.out.println("-----------------------");
		if (led.getLastNotePosition() && !led.isNoteInRobot()) {
			led.rumble();
		} else if (led.isNoteInRobot()) {
			led.blink(led.getColorByMode());
		} else {
			led.setColorByMode();
			System.out.println(led.getColorByMode() + "\n\n\n\n\n\n\n\n\n");
	}
		led.updateNoteState();
}}
