package edu.greenblitz.robotName.commands.shooter;

public class StopFlyWheel extends FlyWheelCommand {

    @Override
    public void initialize() {
        flyWheel.stop();
    }
}
