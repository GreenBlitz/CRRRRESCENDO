package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ReleaseNote extends SequentialCommandGroup {
    public ReleaseNote(){
        super(
                new RunRollerClockWiseUntilObjectIsOut(),
                new RollClockWiseForAGivenTime(RollerConstants.SAFETY_TIME_AFTER_NOTE_IS_RELEASED)
        );
    }
}