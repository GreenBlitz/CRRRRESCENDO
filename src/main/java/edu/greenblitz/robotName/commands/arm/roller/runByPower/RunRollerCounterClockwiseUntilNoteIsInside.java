package edu.greenblitz.robotName.commands.arm.roller.runByPower;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;
import edu.wpi.first.math.geometry.Rotation2d;

public class RunRollerCounterClockwiseUntilNoteIsInside extends RollerCommand {

    private Rotation2d targetPosition;
    
    @Override
    public void initialize() {
        targetPosition = roller.getAngle().plus(RollerConstants.ROTATIONS_TILL_OBJECT_ENTERED);
    }
    
    @Override
    public void execute() {
        roller.rollCounterClockwise();
    }

    @Override
    public boolean isFinished() {
        return roller.isAtAngle(targetPosition);
    }
    
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        roller.setObjectInside(true);
    }
}