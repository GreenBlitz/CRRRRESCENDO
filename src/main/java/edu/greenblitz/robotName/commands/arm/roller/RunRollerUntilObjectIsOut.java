package edu.greenblitz.robotName.commands.arm.roller;

public class RunRollerUntilObjectIsOut extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollClockWise();
    }

    @Override
    public boolean isFinished() {
        return !roller.isObjectInside();
    }
}