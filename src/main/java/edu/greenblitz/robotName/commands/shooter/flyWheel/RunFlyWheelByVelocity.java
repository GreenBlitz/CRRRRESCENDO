package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class RunFlyWheelByVelocity extends FlyWheelCommand {

    private int timeInShootingSpeed;

    double rightWheelVelocity;

    double leftWheelVelocity;

    public RunFlyWheelByVelocity(double velocity) {
        rightWheelVelocity = velocity;
        leftWheelVelocity = velocity * FlyWheelConstants.LEFT_SHOOTING_POWER_CONVERSION_FACTOR;
    }

    protected void changeVelocity(double velocity) {
        rightWheelVelocity = velocity;
        leftWheelVelocity = velocity * FlyWheelConstants.LEFT_SHOOTING_POWER_CONVERSION_FACTOR;
    }

    @Override
    public void initialize() {
        timeInShootingSpeed = 0;
        flyWheel.setVelocity(leftWheelVelocity, rightWheelVelocity);
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            flyWheel.setVelocity(leftWheelVelocity, rightWheelVelocity);
        }
        if (flyWheel.isRightWheelAtVelocity(rightWheelVelocity)
                && flyWheel.isLeftWheelAtVelocity(leftWheelVelocity)) {
            timeInShootingSpeed++;
        } else {
            timeInShootingSpeed = 0;
        }
        flyWheel.setPreparedToShoot(timeInShootingSpeed > FlyWheelConstants.SHOOTING_SPEED_TIME_SECONDS);
    }

    @Override
    public boolean isFinished() {
        return flyWheel.getPreparedToShoot();
    }
    
    @Override
    public void end(boolean interrupted) {
        flyWheel.stop();
    }
}
