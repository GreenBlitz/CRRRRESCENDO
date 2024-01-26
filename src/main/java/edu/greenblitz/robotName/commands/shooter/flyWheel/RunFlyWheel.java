package edu.greenblitz.robotName.commands.shooter.flyWheel;


import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class RunFlyWheel extends FlyWheelCommand {
    @Override
    public void execute() {
        flyWheel.setPower(FlyWheelConstants.SHOOTING_POWER);
    }
}
