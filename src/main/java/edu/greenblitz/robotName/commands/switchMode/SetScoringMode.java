package edu.greenblitz.robotName.commands.switchMode;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class SetScoringMode extends InstantCommand {

    private ScoringMode scoringMode;

    protected SetScoringMode(ScoringMode mode) {
        scoringMode = mode;
    }

    @Override
    public void initialize() {
        SmartDashboard.putString("mode", scoringMode.toString());
        ScoringModeSelector.setScoringMode(scoringMode);
    }

}
