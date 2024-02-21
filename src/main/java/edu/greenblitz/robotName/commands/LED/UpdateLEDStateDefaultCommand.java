package edu.greenblitz.robotName.commands.LED;

import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.LED.LEDConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class UpdateLEDStateDefaultCommand extends GBCommand {
	
	private LED led;

	private Timer ledBlinkingDurationTimer;
	int i;

	public UpdateLEDStateDefaultCommand() {
		led = LED.getInstance();
		require(led);
		ledBlinkingDurationTimer = new Timer();
	}

	@Override
	public void execute() {
		if (led.getLastNotePosition() && !led.isNoteInRobot()) {
			led.rumble();
			i++;
			SmartDashboard.putNumber("hereee",i);
		}
		else if (!led.getLastNotePosition() && led.isNoteInRobot()) {
			ledBlinkingDurationTimer.restart();
		}
		if (led.isNoteInRobot() && ledBlinkingDurationTimer.get() <= LEDConstants.BLINKING_TIME) {
			led.blink(led.getColorByMode());
		} else {
			led.setColorByMode();
		}
		led.updateNoteState();

	}
}
