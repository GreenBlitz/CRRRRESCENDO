package edu.greenblitz.robotName.commands.arm.roller.runByPower;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunRollerCounterClockwiseUntilNoteIsInside extends RollerCommand {
    
    @Override
    public void initialize() {
        super.initialize();
        SmartDashboard.putBoolean("rolly clocky", true);
    }
    
    @Override
    public void execute() {
        roller.rollCounterClockwise();
    }

    @Override
    public boolean isFinished() {
        SmartDashboard.putBoolean("roller object in", roller.isObjectIn());
        return roller.isObjectIn();
    }
}