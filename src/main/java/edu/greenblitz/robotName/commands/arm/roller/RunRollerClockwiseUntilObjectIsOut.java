package edu.greenblitz.robotName.commands.arm.roller;

public class RunRollerClockwiseUntilObjectIsOut extends RollerCommand{

    @Override
    public void execute() {
        roller.rollClockwise();
    }

    @Override
    public boolean isFinished() {
        return !roller.isObjectInside();
    }
}