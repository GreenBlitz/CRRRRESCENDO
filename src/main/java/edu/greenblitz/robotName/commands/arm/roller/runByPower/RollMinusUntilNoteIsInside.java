package edu.greenblitz.robotName.commands.arm.roller.runByPower;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;

public class RollMinusUntilNoteIsInside extends RollerCommand {

    @Override
    public void execute() {
        roller.rollMinus();
    }

    @Override
    public boolean isFinished() {
        return roller.isObjectIn();
    }
}