package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.Field;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.FMSUtils;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;

public class MoveToClimbPosition extends MoveToPosition {

    public MoveToClimbPosition() {
        super(FMSUtils.getAlliance() == DriverStation.Alliance.Red ? getClosestRedClimbPosition() : getClosestBlueClimbPosition());
    }

    public static Pose2d getClosestRedClimbPosition() {
        Pose2d robotPose = SwerveChassis.getInstance().getRobotPose();
        double distanceToLower = robotPose.getTranslation().getDistance(Field.ScoringPositions.RED_LOWER_CLIMB_POSITION.getTranslation());
        double distanceToUpper = robotPose.getTranslation().getDistance(Field.ScoringPositions.RED_UPPER_CLIMB_POSITION.getTranslation());
        double distanceToMid = robotPose.getTranslation().getDistance(Field.ScoringPositions.RED_MID_CLIMB_POSITION.getTranslation());
        double closestDistance = Math.min(distanceToMid, Math.min(distanceToLower, distanceToUpper));
        if (closestDistance == distanceToLower)
            return Field.ScoringPositions.RED_LOWER_CLIMB_POSITION;
        if (closestDistance == distanceToUpper)
            return Field.ScoringPositions.RED_UPPER_CLIMB_POSITION;
        else
            return Field.ScoringPositions.RED_MID_CLIMB_POSITION;
    }

    public static Pose2d getClosestBlueClimbPosition() {
        Pose2d robotPose = SwerveChassis.getInstance().getRobotPose();
        double distanceToLower = robotPose.getTranslation().getDistance(Field.ScoringPositions.BLUE_LOWER_CLIMB_POSITION.getTranslation());
        double distanceToUpper = robotPose.getTranslation().getDistance(Field.ScoringPositions.BLUE_UPPER_CLIMB_POSITION.getTranslation());
        double distanceToMid = robotPose.getTranslation().getDistance(Field.ScoringPositions.BLUE_MID_CLIMB_POSITION.getTranslation());
        double closestDistance = Math.min(distanceToMid, Math.min(distanceToLower, distanceToUpper));
        if (closestDistance == distanceToLower)
            return Field.ScoringPositions.BLUE_LOWER_CLIMB_POSITION;
        if (closestDistance == distanceToUpper)
            return Field.ScoringPositions.BLUE_UPPER_CLIMB_POSITION;
        else
            return Field.ScoringPositions.BLUE_MID_CLIMB_POSITION;
    }
}