package edu.greenblitz.robotName.commands.shooter.flyWheel;


public class StopFlyWheel extends FlyWheelCommand {

    @Override
    public void initialize() {
        flyWheel.stop();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}