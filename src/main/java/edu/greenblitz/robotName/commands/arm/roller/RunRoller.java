package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.subsystems.arm.EndEffector.RollerUtils.RollerConstants;
import edu.greenblitz.robotName.subsystems.arm.Wrist;

public class RunRoller extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollIn();
    }

    @Override
    public boolean isFinished() {
        return !Wrist.getInstance().isObjectInside();
    }

    @Override
    public void end(boolean interrupted) {
        roller.stop();
    }
}
