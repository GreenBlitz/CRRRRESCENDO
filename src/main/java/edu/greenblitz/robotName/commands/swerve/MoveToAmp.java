package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.Field;
import edu.greenblitz.robotName.commands.auto.MoveToPosition;

public class MoveToAmp extends MoveToPosition {

    public MoveToAmp() {
        super(Field.ScoringPositions.AMP_SCORE_POSITION);
    }
}
