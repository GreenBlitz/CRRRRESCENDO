package edu.greenblitz.robotName.commands.arm.roller;

public class RunRollerUntilNoteIsInside extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollOut();
    }

    @Override
    public boolean isFinished() {
        return roller.isObjectInside();
    }

}