package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.intake.CollectNote;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CollectNoteToScoringMode extends SequentialCommandGroup {

    public CollectNoteToScoringMode() {
        super(
                new CollectNote(),
                new ConditionalCommand(
                        new TransferNote(),
                        new GBCommand() {},
                        () -> ScoringModeSelector.isAmpMode()
                )
        );
    }
}
