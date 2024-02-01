package edu.greenblitz.robotName.commands.LED;
import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.LED.LEDConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class BlinkIfInArm extends GBCommand {
	Timer timer;
	double endTime = 5.0;
	public void time(double timeRunning){
		this.endTime = timeRunning;
	}
	
	@Override
	public void require(Subsystem LED) {
		super.require(LED);
	}
	
	@Override
	public void initialize() {
		timer = new Timer();
		timer.start();
		LED.getInstance().startTimer();
	}
	
	@Override
	public void execute() {
		LED.getInstance().blinkIfInArm();
	}
	
	@Override
	public boolean isFinished() {
		return timer.get() > endTime;
		
	}
	
	@Override
	public void end(boolean interrupted) {
		LED.getInstance().setLEDColor(Color.kYellow,0, LEDConstants.LED_LENGTH);
		super.end(interrupted);
	}
}

