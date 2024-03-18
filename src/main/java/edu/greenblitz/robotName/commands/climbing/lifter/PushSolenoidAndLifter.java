package edu.greenblitz.robotName.commands.climbing.lifter;

import edu.greenblitz.robotName.subsystems.climber.lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.climber.solenoid.Solenoid;
import edu.greenblitz.robotName.subsystems.climber.solenoid.SolenoidConstants;
import edu.wpi.first.wpilibj.Timer;

public class PushSolenoidAndLifter extends LifterCommand{

	private Timer timer;

	private Solenoid solenoid;

	public PushSolenoidAndLifter() {
		super();
		solenoid = Solenoid.getInstance();
		timer = new Timer();
		require(solenoid);
	}

	@Override
	public void initialize() {
		timer.restart();
	}

	@Override
	public void execute() {
		solenoid.closeSolenoid();
		lifter.setPower(LifterConstants.LIFTER_POWER_TO_RELEASES_SOLENOID);
	}

	@Override
	public boolean isFinished() {
		return timer.hasElapsed(SolenoidConstants.SHORTER_SECONDS_TO_RELEASE_SOLENOID / 2);
	}

	@Override
	public void end(boolean interrupted) {
		lifter.stop();
		solenoid.holdSolenoid();
	}
}
