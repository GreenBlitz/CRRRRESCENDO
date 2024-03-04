package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class FlyWheelDefaultCommand extends RunFlyWheelByVelocity {

	public FlyWheelDefaultCommand() {
		super(FlyWheelConstants.DEFAULT_VELOCITY);
	}
}
