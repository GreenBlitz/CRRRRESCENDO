package edu.greenblitz.robotName.shootingStateService;


import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.math.geometry.Translation2d;

public class ShootingPositionConstants {
    public static final Translation2d CENTER_OF_SHOOTING_ZONE = new Translation2d(1, 1);
    public static final double SHOOTING_ZONE_RADIUS_IN_METERS = 1;
    public static ShootingZone LEGAL_SHOOTING_ZONE = new ShootingZone(
            CENTER_OF_SHOOTING_ZONE,
            SHOOTING_ZONE_RADIUS_IN_METERS,
            FieldConstants.ShootingZoneConstants.RESTRICTED_BOUNDS
    );
}
