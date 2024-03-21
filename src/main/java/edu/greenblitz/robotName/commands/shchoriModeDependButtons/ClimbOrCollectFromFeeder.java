package edu.greenblitz.robotName.commands.shchoriModeDependButtons;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.climbing.ClimbUp;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteFromFeeder;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class ClimbOrCollectFromFeeder extends ConditionalCommand {

    public ClimbOrCollectFromFeeder(){
        super(
                new ClimbUp(),
                new CollectNoteFromFeeder(),
                ScoringModeSelector::isClimbMode
        );
    }

}
