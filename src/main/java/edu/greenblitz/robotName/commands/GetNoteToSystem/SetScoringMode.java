package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.ScoringMode;

public class SetScoringMode extends GBCommand {

    private ScoringMode scoringMode;

    protected SetScoringMode(ScoringMode mode) {
        scoringMode = mode;
    }

    @Override
    public void initialize() {
        ScoringModeSelector.setScoringMode(scoringMode);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
