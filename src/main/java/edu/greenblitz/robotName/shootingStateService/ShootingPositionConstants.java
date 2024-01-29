package edu.greenblitz.robotName.shootingStateService;


import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.math.geometry.Translation2d;

public class ShootingPositionConstants {
    public static final Translation2d CENTER_OF_SHOOTING_ZONE = FieldConstants.MIDDLE_OF_SPEAKER_POSITION.toTranslation2d();
    public static final double SHOOTING_ZONE_RADIUS_IN_METERS = 3;
    public static ShootingZone LEGAL_SHOOTING_ZONE = new ShootingZone(
            CENTER_OF_SHOOTING_ZONE,
            SHOOTING_ZONE_RADIUS_IN_METERS//,
//            FieldConstants.ShootingZoneConstants.RESTRICTED_BOUNDS
    );
}
