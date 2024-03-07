package edu.greenblitz.robotName.commands.arm.roller.runByPower;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunRollerCounterClockwiseUntilNoteIsInside extends RollerCommand {
    
    @Override
    public void execute() {
        roller.rollCounterClockwise();
    }

    @Override
    public boolean isFinished() {
        return roller.isObjectIn();
    }
    
}