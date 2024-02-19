package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class CheckFlywheelByVelocity extends SystemCheckCommand {

    protected FlyWheel flyWheel;

    private int timeInShootingSpeed;

    private final double rightWheelVelocity = FlyWheelConstants.SHOOTING_VELOCITY;

    private final double leftWheelVelocity = FlyWheelConstants.SHOOTING_VELOCITY * FlyWheelConstants.LEFT_SHOOTING_POWER_CONVERSION_FACTOR;

    public CheckFlywheelByVelocity() {
        flyWheel = FlyWheel.getInstance();
        require(flyWheel);
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
        if (flyWheel.isAtVelocity(rightWheelVelocity, leftWheelVelocity)) {
            timeInShootingSpeed++;
        } else {
            timeInShootingSpeed = 0;
        }
        flyWheel.setPreparedToShoot(timeInShootingSpeed >= FlyWheelConstants.MINIMUM_SHOOTING_SPEED_TIME);
    }

    @Override
    public boolean isFinished() {
        return flyWheel.getPreparedToShoot();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        flyWheel.stop();
    }
}
