package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.arm.ScoreToAmp;
import edu.greenblitz.robotName.commands.shooter.shootingState.GoToAndShootToSpeaker;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class ScoreToScoringMode extends ConditionalCommand {

    public ScoreToScoringMode(){
        super(
                new ScoreToAmp(),
                new GoToAndShootToSpeaker(),
                ScoringModeSelector::isAmpMode
        );
    }
}
