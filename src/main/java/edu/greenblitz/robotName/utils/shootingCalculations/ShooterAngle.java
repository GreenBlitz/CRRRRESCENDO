package edu.greenblitz.robotName.utils.shootingCalculations;

import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.utils.GBMath;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;

public class ShooterAngle {

    /**
     * Gets the position of the shooter and returns the angle it needs to be in order
     * to shoot into the middle of the speaker.
     *
     * @param position The position of the shooter relative to the field.
     * @return The angle in the form of Rotation2d.
     */
    public static Rotation2d getShooterAngleBasedOnPosition(Translation3d position) {
        Translation2d relativeSpeakerPosition = getRelativePositionOfMiddleOfSpeaker(position);
        double angle = Math.atan(relativeSpeakerPosition.getY() / relativeSpeakerPosition.getX());
        return new Rotation2d(angle);
    }

    /**
     * Gets the 3D position of the shooter, uses the position of the middle of the speaker,
     * and returns the 2D position of it where (0,0) is the shooter's position.
     *
     * @param position The position of the shooter relative to the field.
     * @return The 2D position relative to the shooter.
     */
    public static Translation2d getRelativePositionOfMiddleOfSpeaker(Translation3d position) {
        Translation3d Speaker = FieldConstants.MID_SPEAKER_POSITION;
        double x = GBMath.distance(position, new Translation3d(Speaker.getX(), Speaker.getY(), 0));
        double y = Speaker.getZ() - position.getZ();
        return new Translation2d(x, y);
    }
}
