package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class RunFlyWheelByPower extends FlyWheelCommand {

    private double power;

    public RunFlyWheelByPower(double power) {
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