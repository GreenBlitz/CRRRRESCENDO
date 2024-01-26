package edu.greenblitz.robotName.commands.shooter;

public class StopFlyWheel extends FlyWheelCommand {

    @Override
    public void execute() {
        flyWheel.stop();
    }
}
