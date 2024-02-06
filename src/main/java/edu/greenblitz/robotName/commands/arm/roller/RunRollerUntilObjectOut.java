package edu.greenblitz.robotName.commands.arm.roller;

public class RunRollerUntilObjectOut extends RollerCommand{
    @Override
    public void execute() {
        roller.rollOut();
    }

    @Override
    public boolean isFinished() {
        return !roller.isObjectInside();
    }
}
