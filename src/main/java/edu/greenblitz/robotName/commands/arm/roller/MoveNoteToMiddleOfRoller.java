package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;

public class MoveNoteToMiddleOfRoller extends RunRollerByRotations {

    public MoveNoteToMiddleOfRoller() {
        super(RollerConstants.SAFETY_ROTATIONS_TILL_OBJECT_ENTERED);
    }
}