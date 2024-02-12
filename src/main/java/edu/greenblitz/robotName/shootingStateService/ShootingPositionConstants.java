package edu.greenblitz.robotName.shootingStateService;


import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class ShootingPositionConstants {
    
    public static final Translation2d CENTER_OF_SHOOTING_ZONE = FieldConstants.MIDDLE_OF_SPEAKER_POSITION.toTranslation2d();

    public static final double SHOOTING_ZONE_RADIUS_IN_METERS = 4;

    public static final Rotation2d LOWER_ANGLE_LIMIT = Rotation2d.fromDegrees(-50);

    public static final Rotation2d UPPER_ANGLE_LIMIT = Rotation2d.fromDegrees(30);

    public static ShootingZone SHOOTING_ZONE = new ShootingZone(
            CENTER_OF_SHOOTING_ZONE,
            SHOOTING_ZONE_RADIUS_IN_METERS,
            FieldConstants.FieldZones.RESTRICTED_BOUNDS,
            LOWER_ANGLE_LIMIT,
            UPPER_ANGLE_LIMIT
    );
}
