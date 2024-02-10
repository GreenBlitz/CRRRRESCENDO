package edu.greenblitz.robotName.commands.shooter.flyWheel;


import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class RunFlyWheelByPowerConstant extends RunFlyWheelByPower {

	public RunFlyWheelByPowerConstant() {
		super(FlyWheelConstants.SHOOTING_POWER);
	}
}

