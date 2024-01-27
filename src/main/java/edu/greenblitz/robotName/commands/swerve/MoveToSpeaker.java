package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.Field;
import edu.greenblitz.robotName.commands.auto.MoveToPose;

public class MoveToSpeaker extends MoveToPose {

    public MoveToSpeaker() {
        super(Field.ScoringPositions.speakerScorePosition);
    }
}
