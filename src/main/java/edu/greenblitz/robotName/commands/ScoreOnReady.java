package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class ScoreOnReady extends ConditionalCommand {

    public ScoreOnReady(){
        super(
                new ShootOnReady(),
                new ReleaseNoteFromArmOnReady(),
                ScoringModeSelector::isSpeakerMode
        );
    }

}
