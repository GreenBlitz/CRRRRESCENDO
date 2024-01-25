package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;

public class ShootByPower extends FlyWheelCommand {

    private double power;
    public ShootByPower (double power){
        this.power = power;
    }

    @Override
    public void execute() {
        flyWheel.setPower(power);
    }

    @Override
    public boolean isFinished() {
        Funnel.getInstance().is
    }

    @Override
    public void end(boolean interrupted) {
        flyWheel.setPower(0);
    }
}
