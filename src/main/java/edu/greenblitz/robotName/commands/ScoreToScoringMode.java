package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.arm.MoveToAndScoreToAmp;
import edu.greenblitz.robotName.commands.shooter.shootingState.GoToAndShootToSpeaker;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class ScoreToScoringMode extends ConditionalCommand {

    public ScoreToScoringMode(){
        super(
                new MoveToAndScoreToAmp(),
                new GoToAndShootToSpeaker(),
                ScoringModeSelector::isAmpMode
        );
    }
}
