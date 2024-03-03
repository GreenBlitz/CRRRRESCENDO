package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;

public class RollerDefaultCommand extends RollerCommand {

    @Override
    public void execute() {
        if (roller.isObjectIn()) {
            roller.setPower(RollerConstants.CHECK_MOTION_POWER);
        }
    }
}
