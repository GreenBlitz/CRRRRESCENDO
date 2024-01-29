package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.Supplier;

public class SetScoringMode extends GBCommand {

    private ScoringMode scoringMode;

    protected SetScoringMode(ScoringMode mode) {
        scoringMode = mode;
    }

    @Override
    public void initialize() {
        System.out.println(scoringMode);
        ScoringModeSelector.setScoringMode(scoringMode);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
