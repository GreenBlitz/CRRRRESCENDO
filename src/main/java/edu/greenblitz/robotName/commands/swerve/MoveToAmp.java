package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.Field;
import edu.greenblitz.robotName.commands.auto.MoveToPose;

public class MoveToAmp extends SwerveCommand{
    @Override
    public void initialize() {
        MoveToPose.getPathCommand(Field.ScoringPositions.ampScorePosition).schedule();
    }
}
