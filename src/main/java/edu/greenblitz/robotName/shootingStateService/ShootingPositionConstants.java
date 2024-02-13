package edu.greenblitz.robotName.shootingStateService;


import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class ShootingPositionConstants {

    public static final Translation2d CENTER_OF_SHOOTING_ZONE = FieldConstants.MIDDLE_OF_SPEAKER_POSITION.toTranslation2d();

    public static final Rotation2d LEGAL_LOWER_ANGLE_LIMIT = Rotation2d.fromDegrees(-50);

    public static final Rotation2d LEGAL_UPPER_ANGLE_LIMIT = Rotation2d.fromDegrees(30);
    public static final double LEGAL_SHOOTING_ZONE_RADIUS_IN_METERS = 3;

    public static ShootingZone LEGAL_SHOOTING_ZONE;

    static {
        LEGAL_SHOOTING_ZONE = new ShootingZone(
                CENTER_OF_SHOOTING_ZONE,
                LEGAL_SHOOTING_ZONE_RADIUS_IN_METERS,
                FieldConstants.FieldZones.RESTRICTED_BOUNDS,
                LEGAL_LOWER_ANGLE_LIMIT,
                LEGAL_UPPER_ANGLE_LIMIT
        );
    }
    public static final double CLOSE_SHOOTING_ZONE_RADIUS_IN_METERS = 1.5;

    public static ShootingZone CLOSE_SHOOTING_ZONE = new ShootingZone(
            CENTER_OF_SHOOTING_ZONE,
            CLOSE_SHOOTING_ZONE_RADIUS_IN_METERS,
            FieldConstants.FieldZones.RESTRICTED_BOUNDS,
            LEGAL_LOWER_ANGLE_LIMIT,
            LEGAL_UPPER_ANGLE_LIMIT
    );
}