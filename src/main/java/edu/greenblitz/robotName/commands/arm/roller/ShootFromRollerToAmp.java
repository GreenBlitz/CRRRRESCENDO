package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.subsystems.arm.EndEffector.RollerUtils.RollerConstants;
import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ShootFromRollerToAmp extends RollerCommand{
    @Override
    public void initialize() {
        roller.rollIn();
    }

    @Override
    public boolean isFinished() {
        if (!Wrist.getInstance().isObjectInside()){
            new WaitCommand(RollerConstants.TIME_AFTER_OBJECT_IN_ROBOT);
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        roller.stop();
    }
}
