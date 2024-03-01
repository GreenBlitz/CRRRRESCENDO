package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByVelocity;

public class FlyWheelDefaultCommand extends RunFlyWheelByVelocity {
	public FlyWheelDefaultCommand() {
		super(50);
	}
}
