package edu.greenblitz.robotName.commands.arm.roller;

public class RunRollerUntilNoteInside extends RollerCommand{
    @Override
    public void execute() {
        roller.rollIn();
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
