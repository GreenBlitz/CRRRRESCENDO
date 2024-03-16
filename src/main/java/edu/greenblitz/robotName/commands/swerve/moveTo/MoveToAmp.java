package edu.greenblitz.robotName.commands.swerve.moveTo;

import edu.greenblitz.robotName.Field;
import edu.greenblitz.robotName.commands.swerve.moveTo.MoveToPosition;
import edu.greenblitz.robotName.utils.FMSUtils;
import edu.wpi.first.wpilibj.DriverStation;

public class MoveToAmp extends MoveToPosition {

    public MoveToAmp() {
        super(FMSUtils.getAlliance() == DriverStation.Alliance.Red ?
                Field.ScoringPositions.RED_AMP_SCORE_POSITION :
                Field.ScoringPositions.BLUE_AMP_SCORE_POSITION
        );
    }
}