package edu.greenblitz.robotName.commands.shooter;

public class ShootByPower extends FlyWheelCommand {

    private double power;
    public ShootByPower (double power){
        this.power = power;
    }

    @Override
    public void execute() {
        flyWheel.setPower(power,power);
    }

    @Override
    public void end(boolean interrupted) {
        flyWheel.stop();
    }
}
