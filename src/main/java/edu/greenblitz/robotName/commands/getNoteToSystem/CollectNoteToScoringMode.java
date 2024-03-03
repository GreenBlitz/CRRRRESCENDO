package edu.greenblitz.robotName.commands.getNoteToSystem;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.intake.NoteToShooterWithoutArm;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CollectNoteToScoringMode extends SequentialCommandGroup {

    public CollectNoteToScoringMode() {
        super(
                new NoteToShooterWithoutArm(),
                new ConditionalCommand(
                        new TransferNote(),
                        new InstantCommand(),
                        ScoringModeSelector::isAmpMode
                )
        );
    }
}