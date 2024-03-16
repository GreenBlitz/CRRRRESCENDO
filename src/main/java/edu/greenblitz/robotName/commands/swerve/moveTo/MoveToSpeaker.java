package edu.greenblitz.robotName.commands.swerve.moveTo;

import edu.greenblitz.robotName.Field;
import edu.greenblitz.robotName.utils.FMSUtils;
import edu.wpi.first.wpilibj.DriverStation;

public class MoveToSpeaker extends MoveToPosition {

    public MoveToSpeaker() {
        super(FMSUtils.getAlliance() == DriverStation.Alliance.Red ?
                Field.ScoringPositions.RED_SPEAKER_SCORE_POSITION :
                Field.ScoringPositions.BLUE_SPEAKER_SCORE_POSITION
        );
    }
}