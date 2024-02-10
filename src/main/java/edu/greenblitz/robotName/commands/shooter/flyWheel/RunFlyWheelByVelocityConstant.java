package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class RunFlyWheelByVelocityConstant extends RunFlyWheelByVelocity{

    public RunFlyWheelByVelocityConstant() {
        super(FlyWheelConstants.SHOOTING_POWER * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }
}
