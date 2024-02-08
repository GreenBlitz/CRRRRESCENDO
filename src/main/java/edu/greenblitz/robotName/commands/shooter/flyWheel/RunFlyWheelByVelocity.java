package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.commands.shooter.flyWheel.FlyWheelCommand;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class RunFlyWheelByVelocity extends FlyWheelCommand {

    private int timeInShootingSpeed;

    private double velocity;

    public RunFlyWheelByVelocity(double velocity) {
        this.velocity = velocity;
    }

    @Override
    public void initialize() {
        timeInShootingSpeed = 0;
    }

    @Override
    public void execute() {
        double rightWheelVelocity = velocity;
        double leftWheelVelocity = velocity * FlyWheelConstants.LEFT_SHOOTING_POWER_CONVERSION_FACTOR;
        flyWheel.setVelocity(leftWheelVelocity, rightWheelVelocity);
        if (flyWheel.isRightWheelAtVelocity(rightWheelVelocity) && flyWheel.isLeftWheelAtVelocity(leftWheelVelocity)) {
            timeInShootingSpeed++;
        } else {
            timeInShootingSpeed = 0;
        }
        flyWheel.setPreparedToShoot(timeInShootingSpeed > FlyWheelConstants.SHOOTING_SPEED_TIME);
    }

    @Override
    public boolean isFinished() {
        return flyWheel.getPreparedToShoot();
    }
}
