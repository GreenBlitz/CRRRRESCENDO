package edu.greenblitz.robotName.commands.climbing.lifter;

import edu.greenblitz.robotName.subsystems.climber.lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.climber.solenoid.Solenoid;
import edu.greenblitz.robotName.subsystems.climber.solenoid.SolenoidConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.Timer;

public class PushSolenoidWheel extends LifterCommand {

	private Timer timer;

	private Solenoid solenoid;

	public PushSolenoidWheel() {
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
		lifter.setPower(LifterConstants.LIFTER_POWER_TO_RELEASES_SOLENOID);
		if (timer.hasElapsed(SolenoidConstants.SECONDS_TO_RELEASE_SOLENOID / 2)){
			solenoid.closeSolenoid();
		}
	}

	@Override
	public boolean isFinished() {
		return timer.hasElapsed(SolenoidConstants.SECONDS_TO_RELEASE_SOLENOID);
	}

	@Override
	public void end(boolean interrupted) {
		lifter.stop();
		solenoid.holdSolenoid();
	}
}
