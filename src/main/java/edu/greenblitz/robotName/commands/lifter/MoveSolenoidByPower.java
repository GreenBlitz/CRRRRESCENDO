package edu.greenblitz.robotName.commands.lifter;

public class MoveSolenoidByPower extends LifterCommand{


    private double power;

    public MoveSolenoidByPower(double power) {
        super();
        this.power = power;
    }

    @Override
    public void execute() {
        lifter.moveSolenoidByPower(power);
    }

    @Override
    public void end(boolean interrupted) {
        lifter.moveSolenoidByPower(0);
    }
}
