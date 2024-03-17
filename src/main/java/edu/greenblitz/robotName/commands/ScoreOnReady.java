package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ProxyCommand;

public class ScoreOnReady extends ProxyCommand {

    public ScoreOnReady(){
        super(ScoreOnReady::getCommand);
    }

    public static Command getCommand(){
        if (ScoringModeSelector.isSpeakerMode()){
            return new ShootOnReady();
        }
        else {
            return new ReleaseNoteFromArmOnReady();
        }
    }

}
