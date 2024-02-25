package edu.greenblitz.robotName.commands.arm.roller.runByPower;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;
import edu.wpi.first.math.geometry.Rotation2d;

public class RunRollerClockwiseUntilObjectIsOut extends RollerCommand {
    
    private Rotation2d targetPosition;
    
    @Override
    public void initialize() {
        targetPosition = roller.getAngle().plus(RollerConstants.ROTATIONS_TILL_OBJECT_EXITED);
    }
    
    @Override
    public void execute() {
        roller.rollClockwise();
    }

    @Override
    public boolean isFinished() {
        return roller.isAtAngle(targetPosition);
    }
    
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        roller.setObjectInside(false);
    }
}