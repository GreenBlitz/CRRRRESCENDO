package edu.greenblitz.robotName.commands.arm.roller;

public class RunRollerCounterClockWiseUntilNoteIsInside extends RollerCommand{

    @Override
    public void execute() {
        roller.rollCounterClockWise();
    }

    @Override
    public boolean isFinished() {
        return roller.isObjectInside();
    }

}