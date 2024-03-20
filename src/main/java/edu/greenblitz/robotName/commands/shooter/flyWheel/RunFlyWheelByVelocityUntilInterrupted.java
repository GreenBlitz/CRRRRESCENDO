package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class RunFlyWheelByVelocityUntilInterrupted extends FlyWheelCommand {

    private double rightWheelVelocity;

    private double leftWheelVelocity;

    private int timeInShootingSpeed;

    private SmartJoystick joystick;

    public RunFlyWheelByVelocityUntilInterrupted(double velocity, SmartJoystick joystick) {
        rightWheelVelocity = velocity;
        leftWheelVelocity = velocity * FlyWheelConstants.LEFT_SHOOTING_POWER_CONVERSION_FACTOR;
        this.joystick = joystick;
    }

    public RunFlyWheelByVelocityUntilInterrupted(double velocity) {
        rightWheelVelocity = velocity;
        leftWheelVelocity = velocity * FlyWheelConstants.LEFT_SHOOTING_POWER_CONVERSION_FACTOR;
        this.joystick = null;
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
        if (flyWheel.isAtVelocity(rightWheelVelocity, leftWheelVelocity)) {
            timeInShootingSpeed++;
        } else {
            timeInShootingSpeed = 0;
        }
        flyWheel.setPreparedToShoot(timeInShootingSpeed >= FlyWheelConstants.MINIMUM_SHOOTING_SPEED_TIME_ROBORIO_CYCLES);
    }

    @Override
    public boolean isFinished() {
        if (flyWheel.getPreparedToShoot()) {
            if (joystick!= null)
                joystick.rumble(true, 0.4);
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (joystick!= null)
            joystick.rumble(true, 0);
        flyWheel.stop();
    }

}
