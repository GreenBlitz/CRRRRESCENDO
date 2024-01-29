package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.Supplier;

public class SetScoringMode extends GBCommand {

    private Supplier<ScoringMode> scoringMode;

    protected SetScoringMode(Supplier<ScoringMode> mode) {
        scoringMode = mode;
    }

    @Override
    public void initialize() {
        System.out.println(scoringMode.get());
        ScoringModeSelector.setScoringMode(scoringMode.get());
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
