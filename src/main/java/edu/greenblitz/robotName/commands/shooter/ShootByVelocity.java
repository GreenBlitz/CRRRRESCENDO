package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.flyWheel.FlyWheelCommand;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class ShootByVelocity extends FlyWheelCommand {

    private int timeInShootingSpeed;

    private double velocity;

    public ShootByVelocity(double velocity) {
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
    public void end(boolean interrupted) {
        flyWheel.stop();
    }
}
