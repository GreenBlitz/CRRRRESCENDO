package edu.greenblitz.robotName.commands.arm.roller;

public class RunRollerCounterClockwiseUntilNoteIsInside extends RollerCommand{

    @Override
    public void execute() {
        roller.rollCounterClockwise();
    }

    @Override
    public boolean isFinished() {
        return roller.isObjectInside();
    }

}