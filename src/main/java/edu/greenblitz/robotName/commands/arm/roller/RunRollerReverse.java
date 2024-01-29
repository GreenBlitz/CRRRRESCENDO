package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.subsystems.arm.Wrist;

public class RunRollerReverse extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollOut();
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
