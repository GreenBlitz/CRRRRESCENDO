package edu.greenblitz.robotName.commands.arm.roller;

public class RunRollerUntilObjectOut extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollIn();
    }

    @Override
    public boolean isFinished() {
        return !roller.isObjectInside();
    }
}