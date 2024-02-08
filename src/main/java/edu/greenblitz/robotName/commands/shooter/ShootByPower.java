package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class ShootByPower extends FlyWheelCommand {

    private double power;

    public ShootByPower(double power) {
        this.power = power;
    }

    @Override
    public void execute() {
        flyWheel.setPower(power, power * FlyWheelConstants.LEFT_SHOOTING_POWER_CONVERSION_FACTOR);
    }

    @Override
    public void end(boolean interrupted) {
        flyWheel.stop();
    }
}
