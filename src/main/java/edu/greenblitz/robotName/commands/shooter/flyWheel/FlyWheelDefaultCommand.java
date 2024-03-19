package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.DeferredCommand;
import edu.wpi.first.wpilibj2.command.ProxyCommand;

import java.util.Set;

public class FlyWheelDefaultCommand extends FlyWheelCommand {

    double rightWheelVelocity;

    double leftWheelVelocity;

    private int timeInShootingSpeed;


    @Override
    public void initialize() {
        timeInShootingSpeed = 0;
        if (DriverStation.isAutonomous()) {
            rightWheelVelocity = FlyWheelConstants.SHOOTING_VELOCITY;
            leftWheelVelocity = FlyWheelConstants.SHOOTING_VELOCITY * FlyWheelConstants.LEFT_SHOOTING_POWER_CONVERSION_FACTOR;
        }
        else {
            rightWheelVelocity = FlyWheelConstants.DEFAULT_VELOCITY;
            leftWheelVelocity = FlyWheelConstants.DEFAULT_VELOCITY * FlyWheelConstants.LEFT_SHOOTING_POWER_CONVERSION_FACTOR;
        }
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
