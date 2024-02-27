package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class RunFlyWheelByVelocityWithoutIsFinished extends FlyWheelCommand{

	private int timeInShootingSpeed;

	double rightWheelVelocity;

	double leftWheelVelocity;

	public RunFlyWheelByVelocityWithoutIsFinished(double velocity) {
		rightWheelVelocity = velocity;
		leftWheelVelocity = velocity * FlyWheelConstants.LEFT_SHOOTING_POWER_CONVERSION_FACTOR;
	}

	protected void changeVelocity(double velocity) {
		rightWheelVelocity = velocity;
		leftWheelVelocity = velocity * FlyWheelConstants.LEFT_SHOOTING_POWER_CONVERSION_FACTOR;
	}

	@Override
	public void initialize() {
		timeInShootingSpeed = 0;
		flyWheel.setVelocity(leftWheelVelocity, rightWheelVelocity);
	}

	@Override
	public void execute() {
		if (Robot.isSimulation()) {
			flyWheel.setVelocity(leftWheelVelocity, rightWheelVelocity);
		}
		if (flyWheel.isAtVelocity(rightWheelVelocity, leftWheelVelocity)) {
			timeInShootingSpeed++;
		} else {
			timeInShootingSpeed = 0;
		}
		flyWheel.setPreparedToShoot(timeInShootingSpeed >= FlyWheelConstants.MINIMUM_SHOOTING_SPEED_TIME_ROBORIO_CYCLES);
	}

	@Override
	public void end(boolean interrupted) {
		flyWheel.stop();
	}

}
