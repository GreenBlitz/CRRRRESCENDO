package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.subsystems.shooter.ShooterConstants;

public class ShootByVelocity extends ShooterCommand {

    private int isAtShootingSpeed;
    private double velocity;

    public ShootByVelocity(double velocity) {
        this.velocity = velocity;
    }

    public boolean isFlywheelAtVelocity() {
        return Math.abs(shooter.getVelocity() - velocity) < ShooterConstants.EPSILON_RPM;
    }

    @Override
    public void initialize() {
        isAtShootingSpeed = 0;
    }

    @Override
    public void execute() {
        shooter.setVelocity(velocity);
        if (isFlywheelAtVelocity()) {
            isAtShootingSpeed++;
        } else {
            isAtShootingSpeed = 0;
        }
        shooter.setPreparedToShoot(isAtShootingSpeed > ShooterConstants.SHOOTING_SPEED_TIME);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stop();
    }
}
