package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.flyWheel.FlyWheelCommand;

public class StopFlyWheel extends FlyWheelCommand {

    @Override
    public void initialize() {
        flyWheel.stop();
    }
}
