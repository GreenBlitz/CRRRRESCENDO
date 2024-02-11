package edu.greenblitz.robotName.commands.arm.roller.RunByPower;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;

public class RunRollerCounterClockwiseUntilNoteIsInside extends RollerCommand {

    @Override
    public void execute() {
        roller.rollCounterClockwise();
    }

    @Override
    public boolean isFinished() {
        return roller.isObjectIn();
    }

}