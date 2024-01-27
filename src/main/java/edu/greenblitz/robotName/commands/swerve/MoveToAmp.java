package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.Field;
import edu.greenblitz.robotName.commands.auto.MoveToPose;

public class MoveToAmp extends MoveToPose{

    public MoveToAmp() {
        super(Field.ScoringPositions.AMP_SCORE_POSITION);
    }
}
