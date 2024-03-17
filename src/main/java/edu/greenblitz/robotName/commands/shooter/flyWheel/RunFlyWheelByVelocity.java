package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class RunFlyWheelByVelocity extends FlyWheelCommand {

    double rightWheelVelocity;

    double leftWheelVelocity;

    private int timeInShootingSpeed;

    double spin;

    public RunFlyWheelByVelocity(double velocity) {
        rightWheelVelocity = velocity;
        leftWheelVelocity = velocity * FlyWheelConstants.LEFT_SHOOTING_POWER_CONVERSION_FACTOR;
    }

    public RunFlyWheelByVelocity(double velocity, double spin) {
        this.spin = spin;
        rightWheelVelocity = velocity;
        leftWheelVelocity = velocity * spin;
    }

    protected void changeVelocity(double velocity) {
        rightWheelVelocity = velocity;
        leftWheelVelocity = velocity * FlyWheelConstants.LEFT_SHOOTING_POWER_CONVERSION_FACTOR;
    }

    @Override
    public void initialize() {
        timeInShootingSpeed = 0;
        flyWheel.setVelocity(leftWheelVelocity, rightWheelVelocity);
        flyWheel.setPreparedToShoot(false);
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            flyWheel.setVelocity(leftWheelVelocity, rightWheelVelocity);
        }
        if (flyWheel.isAtVelocity(leftWheelVelocity, rightWheelVelocity)) {
            timeInShootingSpeed++;
        } else {
            timeInShootingSpeed = 0;
        }
        flyWheel.setPreparedToShoot(timeInShootingSpeed >= FlyWheelConstants.MINIMUM_SHOOTING_SPEED_TIME_ROBORIO_CYCLES);
    }

    @Override
    public boolean isFinished() {
        return flyWheel.getPreparedToShoot();
    }

}