package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class RotateByScoringMode extends ConditionalCommand {

    public RotateByScoringMode() {
        super(
                new RotateToSpeakerByCalculation(),
                new RotateToAmp(),
                () -> ScoringModeSelector.isSpeakerMode()
        );
    }
}
