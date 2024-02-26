package edu.greenblitz.robotName.commands.arm.roller.runByPower;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;

public class RollPlus extends RollerCommand {

    @Override
    public void execute() {
        roller.rollPlus();
    }

}