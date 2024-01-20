package edu.greenblitz.robotName.commands.shooter;

public class ShootByPower extends FlyWheelCommand {

    private double power;
    public ShootByPower (double power){
        this.power = power;
    }

    @Override
    public void execute() {
        flyWheelCommand.setPower(power);
    }

    @Override
    public void end(boolean interrupted) {
        flyWheelCommand.setPower(0);
    }
}
