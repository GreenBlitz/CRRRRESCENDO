package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class RunFlyWheelByVelocityConstant extends RunFlyWheelByVelocity{

    public RunFlyWheelByVelocityConstant() {
        super(FlyWheelConstants.SHOOTING_VELOCITY);
    }
}
