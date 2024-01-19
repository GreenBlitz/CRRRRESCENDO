package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.utils.GBCommand;

public class ShootByPower extends ShooterCommand {

    private double power;
    public ShootByPower (double power){
        this.power = power;
    }

    @Override
    public void execute() {
        shooter.setPower(power);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setPower(0);
    }
}
