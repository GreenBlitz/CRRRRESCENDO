package edu.greenblitz.robotName.commands.climbing.lifter;

import edu.greenblitz.robotName.subsystems.climber.lifter.LifterConstants;
import edu.wpi.first.wpilibj.Timer;

public class PushSolenoidWheelOnly extends LifterCommand{
	private Timer timer;
	public PushSolenoidWheelOnly(){
		super();
		timer = new Timer();
	}

	@Override
	public void initialize() {
		timer.restart();
	}

	@Override
	public void execute() {
		lifter.setPower(LifterConstants.LIFTER_POWER_TO_RELEASES_SOLENOID);
	}

	@Override
	public boolean isFinished() {
		return
	}
}
