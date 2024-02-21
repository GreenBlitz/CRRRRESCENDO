package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.Robot;

public class StopFlyWheel extends FlyWheelCommand {

    @Override
    public void initialize() {
        if (Robot.isSimulation()) {
            new ShootSimulationNote().schedule();
        }

        flyWheel.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}