package edu.greenblitz.robotName.commands.getNoteToSystem;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.arm.roller.MoveNoteToMiddleOfRoller;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class SafeMoveNoteBetweenShooterAndArm extends ConditionalCommand {

    public SafeMoveNoteBetweenShooterAndArm(){
        super(
                new MoveNoteBetweenShooterAndArm(),
                new SequentialCommandGroup(
                        new MoveNoteBetweenShooterAndArm(),
                        new MoveNoteToMiddleOfRoller()
                ),
                ScoringModeSelector::isSpeakerMode

        );
    }


}
