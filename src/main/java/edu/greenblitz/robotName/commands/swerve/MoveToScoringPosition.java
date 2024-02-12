package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class MoveToScoringPosition extends ConditionalCommand {

    public MoveToScoringPosition() {
        super(
                new MoveToSpeaker(),
                new MoveToAmp(),
                ScoringModeSelector::isSpeakerMode
        );
    }
}