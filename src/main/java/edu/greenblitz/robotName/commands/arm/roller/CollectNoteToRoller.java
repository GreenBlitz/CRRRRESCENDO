package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.commands.arm.roller.runByPower.RunRollerCounterClockwiseUntilNoteIsInside;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CollectNoteToRoller extends SequentialCommandGroup {

    public CollectNoteToRoller(){
        super(
                new RunRollerCounterClockwiseUntilNoteIsInside(),
                new MoveNoteToMiddleOfRoller()
        );
    }

}
