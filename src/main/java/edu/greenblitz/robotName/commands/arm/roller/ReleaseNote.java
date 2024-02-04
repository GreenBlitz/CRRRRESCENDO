package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ReleaseNote extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollIn();
    }

    @Override
    public boolean isFinished() {
        if (!roller.isObjectInside()){
            new WaitCommand(RollerConstants.TIME_UNTIL_NOTE_EXIT_ROLLER).schedule();
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        roller.stop();
    }
}
