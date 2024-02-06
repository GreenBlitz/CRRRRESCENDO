package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.Field;
import edu.greenblitz.robotName.commands.auto.MoveToPosition;
import edu.greenblitz.robotName.utils.FMSUtils;
import edu.wpi.first.wpilibj.DriverStation;

public class MoveToClimbPosition extends MoveToPosition {

    public MoveToClimbPosition() {
        super(FMSUtils.getAlliance() == DriverStation.Alliance.Red ? Field.ScoringPositions.RED_CLIMB_POSITION : Field.ScoringPositions.BLUE_CLIMB_POSITION);
    }
}
