package edu.greenblitz.robotName.commands.climbing.lifter;

import edu.greenblitz.robotName.subsystems.climber.lifter.LifterConstants;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SlowRetractLifter extends LifterCommand {

	private Timer timer;

	private double startingPose;

	private double power;

	public SlowRetractLifter() {
		super();
		timer = new Timer();
	}

	@Override
	public void initialize() {
		timer.restart();
		startingPose = lifter.getPosition();
		power = lifter.getMaxCurrent() > LifterConstants.END_OF_CLIMB_CURRENT ? LifterConstants.END_OF_CLIMB_POWER: LifterConstants.POWER_TO_RETRACT_LIFTER_SLOW;
	}

	@Override
	public void execute() {
		lifter.setPower(power);
	}

	@Override
	public boolean isFinished() {
		return timer.hasElapsed(LifterConstants.TIME_TO_RETRACT_SLOW)
				|| Math.abs(lifter.getPosition() - startingPose) >= LifterConstants.DISTANCE_TO_TRAVEL_SLOW;
	}

	@Override
	public void end(boolean interrupted) {
		lifter.stop();
		timer.stop();
	}

}
