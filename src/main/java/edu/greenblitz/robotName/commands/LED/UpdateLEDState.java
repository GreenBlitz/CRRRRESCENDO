package edu.greenblitz.robotName.commands.LED;

import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.LED.LEDConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.Timer;


public class UpdateLEDState extends GBCommand {
	
	private LED led;
	
	private Timer timer;
	
	public boolean isBlinking;
	
	public UpdateLEDState() {
		led = LED.getInstance();
		require(led);
		timer = new Timer();
		isBlinking = false;
	}
	
	@Override
	public void execute() {
		if(led.shouldBlink()){
			if (!isBlinking)
				timer.restart();
			isBlinking = true;
			
		}
		else {
			led.setColorByMode();
			isBlinking = false;
			timer.stop();
		}
		
		if (isBlinking){
			led.blink(timer.get());
		}
		
	}
}
