package edu.greenblitz.robotName.commands.arm.roller;

public class RunRollerUntilNoteInside extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollOut();
    }

    @Override
    public boolean isFinished() {
        return roller.isObjectInside();
    }

    @Override
    public void end(boolean interrupted) {
        roller.stop();
    }
}
