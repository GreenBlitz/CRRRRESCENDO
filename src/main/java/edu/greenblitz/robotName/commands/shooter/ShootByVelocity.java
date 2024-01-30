package edu.greenblitz.robotName.commands.shooter;

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
        flyWheel.setVelocity(velocity);
        if (flyWheel.isAtVelocity(velocity)) {
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
