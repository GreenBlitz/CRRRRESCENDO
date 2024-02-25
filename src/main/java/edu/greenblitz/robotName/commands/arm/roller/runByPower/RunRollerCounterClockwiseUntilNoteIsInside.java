package edu.greenblitz.robotName.commands.arm.roller.runByPower;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;
import edu.wpi.first.math.geometry.Rotation2d;

public class RunRollerCounterClockwiseUntilNoteIsInside extends RollerCommand {
    
    @Override
    public void execute() {
        roller.rollCounterClockwise();
    }

    @Override
    public boolean isFinished() {
        return roller.isObjectIn();
    }
    
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}