package edu.greenblitz.robotName.utils.shootingCalculations;


import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.utils.GBMath;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;

public class ShooterAngle {

    public static Rotation2d getShooterAngleBasedOnPosition(Translation3d position) {
        Translation2d relativeSpeakerPosition = getRelativePositionOfMiddleOfSpeaker(position);
        double angle = Math.atan(relativeSpeakerPosition.getY() / relativeSpeakerPosition.getX());
        return new Rotation2d(angle);
    }

    public static Translation2d getRelativePositionOfMiddleOfSpeaker(Translation3d position) {
        Translation3d Speaker = FieldConstants.MID_SPEAKER_POSITION;
        double x = GBMath.distance(position, new Translation3d(Speaker.getX(), Speaker.getY(), 0));
        double y = Speaker.getZ() - position.getZ();
        return new Translation2d(x, y);
    }
}