package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class RunFlyWheel extends FlyWheelCommand {
    @Override
    public void execute() {
        flyWheel.setPower(FlyWheelConstants.SHOOTING_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        new StopFlyWheel();
    }
}