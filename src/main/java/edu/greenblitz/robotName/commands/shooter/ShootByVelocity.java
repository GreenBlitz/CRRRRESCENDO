package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class ShootByVelocity extends FlyWheelCommand {

    private int isAtShootingSpeed;
    private double velocity;

    public ShootByVelocity(double velocity) {
        this.velocity = velocity;
    }

    public boolean isFlywheelAtVelocity() {
        return Math.abs(flyWheelCommand.getVelocity() - velocity) < FlyWheelConstants.EPSILON_RPM;
    }

    @Override
    public void initialize() {
        isAtShootingSpeed = 0;
    }

    @Override
    public void execute() {
        flyWheelCommand.setVelocity(velocity);
        if (isFlywheelAtVelocity()) {
            isAtShootingSpeed++;
        } else {
            isAtShootingSpeed = 0;
        }
        flyWheelCommand.setPreparedToShoot(isAtShootingSpeed > FlyWheelConstants.SHOOTING_SPEED_TIME);
    }

    @Override
    public void end(boolean interrupted) {
        flyWheelCommand.stop();
    }
}
