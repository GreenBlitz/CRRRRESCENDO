package edu.greenblitz.robotName.commands.arm.roller.runByPower;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;

public class RollPlusUntilObjectIsOut extends RollerCommand {

    @Override
    public void execute() {
        roller.rollPlus();
    }

    @Override
    public boolean isFinished() {
        return !roller.isObjectIn();
    }
}