package edu.greenblitz.robotName.commands.shooter.flyWheel;

public class RunFlyWheelByPower extends FlyWheelCommand {

    private double power;

    public RunFlyWheelByPower(double power) {
        this.power = power;
    }

    @Override
    public void execute() {
        flyWheel.setPower(power);
    }

}