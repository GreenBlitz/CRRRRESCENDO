package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.Field;
import edu.greenblitz.robotName.commands.auto.MoveToPose;

public class MoveToClimbPosition extends MoveToPose{

    public MoveToClimbPosition() {
        super(Field.ScoringPositions.climbPosition);
    }
}
