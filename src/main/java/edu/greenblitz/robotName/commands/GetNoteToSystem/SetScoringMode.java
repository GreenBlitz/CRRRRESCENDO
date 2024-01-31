package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetScoringMode extends GBCommand {

    private ScoringMode scoringMode;

    protected SetScoringMode(ScoringMode mode) {
        scoringMode = mode;
    }

    @Override
    public void initialize() {
        SmartDashboard.putString("mode", scoringMode.toString());
        ScoringModeSelector.setScoringMode(scoringMode);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
