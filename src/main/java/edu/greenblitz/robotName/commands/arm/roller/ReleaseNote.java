package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.commands.arm.roller.RunByPower.RollClockwiseForAGivenTime;
import edu.greenblitz.robotName.commands.arm.roller.RunByPower.RunRollerClockwiseUntilObjectIsOut;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ReleaseNote extends SequentialCommandGroup {
    public ReleaseNote(){
        super(
                new RunRollerClockwiseUntilObjectIsOut(),
                new RollClockwiseForAGivenTime(RollerConstants.SAFETY_TIME_AFTER_NOTE_IS_RELEASED)
        );
    }
}