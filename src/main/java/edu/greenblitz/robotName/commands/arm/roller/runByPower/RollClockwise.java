package edu.greenblitz.robotName.commands.arm.roller.runByPower;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;

public class RollClockwise extends RollerCommand {

    @Override
    public void execute() {
        roller.rollClockwise();
    }

}