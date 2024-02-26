package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class MoveNoteForTrap extends ConditionalCommand {

    public MoveNoteForTrap() {
        super(
                new RunRollerByRotations(RollerConstants.ROTATIONS_TILL_NOTE_IN_TRAP_POSITION),
                new InstantCommand(),
                () -> Roller.getInstance().isObjectIn()
        );
    }
}

