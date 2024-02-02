package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.subsystems.arm.EndEffector.RollerUtils.RollerConstants;
import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ReleaseNote extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollIn();
    }

    @Override
    public boolean isFinished() {
        if (!Wrist.getInstance().isObjectInside()){
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
