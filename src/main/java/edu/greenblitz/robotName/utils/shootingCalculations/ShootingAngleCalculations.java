package edu.greenblitz.robotName.utils.shootingCalculations;


import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotInterpolationMap;
import edu.greenblitz.robotName.utils.GBMath;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;

public class ShootingAngleCalculations {

    public static Rotation2d getShootingAngle(Translation3d robotPosition) {
        return Rotation2d.fromRadians(
                PivotInterpolationMap.DISTANCE_TO_ANGLE
                        .get(robotPosition.getDistance(FieldConstants.MIDDLE_OF_SPEAKER_POSITION))
        );
    }

    public static Translation2d getPositionOfSpeakerRelativeToShooter(Translation3d shooterPosition) {
        Translation3d speaker = FieldConstants.MIDDLE_OF_SPEAKER_POSITION;

        double distanceInMeters = GBMath.distance(
                shooterPosition,
                new Translation3d(
                        speaker.getX(),
                        speaker.getY(),
                        shooterPosition.getZ()
                )
        );
        double differenceHeightInMeters = speaker.getZ() - shooterPosition.getZ();

        Translation2d relativeSpeakerPosition = new Translation2d(distanceInMeters, differenceHeightInMeters);
        return relativeSpeakerPosition;
    }
}