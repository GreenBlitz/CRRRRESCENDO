package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.Field;
import edu.greenblitz.robotName.commands.auto.MoveToPosition;

public class MoveToSpeaker extends MoveToPosition {

    public MoveToSpeaker() {
        super(Field.ScoringPositions.SPEAKER_SCORE_POSITION);
    }
}
