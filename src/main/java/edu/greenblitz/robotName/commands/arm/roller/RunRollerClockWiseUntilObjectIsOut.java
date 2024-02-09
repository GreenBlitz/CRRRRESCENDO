package edu.greenblitz.robotName.commands.arm.roller;

public class RunRollerClockWiseUntilObjectIsOut extends RollerCommand{

    @Override
    public void execute() {
        roller.rollClockWise();
    }

    @Override
    public boolean isFinished() {
        return !roller.isObjectInside();
    }
}