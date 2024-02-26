package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.commands.arm.roller.runByPower.RollPlusForAGivenTime;
import edu.greenblitz.robotName.commands.arm.roller.runByPower.RollPlusUntilObjectIsOut;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ReleaseNoteFromRoller extends SequentialCommandGroup {

    public ReleaseNoteFromRoller() {
        super(
                new RollPlusUntilObjectIsOut(),
                new RollPlusForAGivenTime(RollerConstants.SAFETY_TIME_AFTER_NOTE_IS_RELEASED)
        );
    }
}