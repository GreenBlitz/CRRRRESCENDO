package edu.greenblitz.robotName.utils.shootingCalculations;


import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.utils.GBMath;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;

public class ShootingAngleCalculations {

    public static Rotation2d getShootingAngleBasedOnPosition(Translation3d position) {
        Translation2d relativeSpeakerPosition = getRelativePositionToMiddleOfSpeaker(position);
        double targetAngle = Math.atan(relativeSpeakerPosition.getY() / relativeSpeakerPosition.getX());
        return new Rotation2d(-targetAngle);
    }

    public static Translation2d getRelativePositionToMiddleOfSpeaker(Translation3d position) {
        Translation3d Speaker = FieldConstants.MIDDLE_OF_SPEAKER_POSITION;
        double x = GBMath.distance(position, new Translation3d(Speaker.getX(), Speaker.getY(), position.getZ()));
        double y = Speaker.getZ() - position.getZ();
        return new Translation2d(x, y);
    }
}