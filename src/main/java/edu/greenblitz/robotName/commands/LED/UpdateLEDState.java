package edu.greenblitz.robotName.commands.LED;

import com.ctre.phoenix6.signals.Led1OffColorValue;
import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.LED.LEDConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.Timer;


public class UpdateLEDState extends GBCommand {
	
	private LED led;

	public boolean isBlinking;
	
	public UpdateLEDState() {
		led = LED.getInstance();
		require(led);
	}
	
	@Override
	public void execute() {
		if(led.isNoteInRobot() && !isBlinking){
			led.blink(led.getColorByMode());
			isBlinking = true;
		} else if(led.shouldRumble()){
			led.rumble();
		} else {
			led.setColorByMode();
			isBlinking = false;
		}

		
	}
}
