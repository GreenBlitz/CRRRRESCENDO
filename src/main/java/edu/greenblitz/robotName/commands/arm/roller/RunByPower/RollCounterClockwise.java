package edu.greenblitz.robotName.commands.arm.roller.RunByPower;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;

public class RollCounterClockwise extends RollerCommand {

    @Override
    public void execute() {
        roller.rollCounterClockwise();
    }

}