package edu.greenblitz.robotName.commands.switchMode;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.ScoringMode;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class SetScoringMode extends InstantCommand {

    private ScoringMode scoringMode;

    public SetScoringMode(ScoringMode mode) {
        scoringMode = mode;
    }

    @Override
    public void initialize() {
        ScoringModeSelector.setScoringMode(scoringMode);
    }
}