package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.Field;
import edu.greenblitz.robotName.commands.auto.MoveToPosition;

public class MoveToClimbPosition extends MoveToPosition {

    public MoveToClimbPosition() {
        super(Field.ScoringPositions.CLIMB_POSITION);
    }
}
