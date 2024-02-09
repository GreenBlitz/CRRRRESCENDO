package edu.greenblitz.robotName.shootingStateService;


import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.math.geometry.Translation2d;

public class ShootingPositionConstants {
    
    public static final Translation2d CENTER_OF_SHOOTING_ZONE = FieldConstants.MIDDLE_OF_SPEAKER_POSITION.toTranslation2d();

    public static final double OPTIMAL_SHOOTING_ZONE_RADIUS_IN_METERS = 2.5;

    public static final double LEGAL_SHOOTING_ZONE_RADIUS_IN_METERS = 3;

    public static ShootingZone OPTIMAL_SHOOTING_ZONE = new ShootingZone(
            CENTER_OF_SHOOTING_ZONE,
            OPTIMAL_SHOOTING_ZONE_RADIUS_IN_METERS,
            FieldConstants.FieldZones.RESTRICTED_BOUNDS
    );

    public static ShootingZone LEGAL_SHOOTING_ZONE = new ShootingZone(
            CENTER_OF_SHOOTING_ZONE,
            LEGAL_SHOOTING_ZONE_RADIUS_IN_METERS,
            FieldConstants.FieldZones.RESTRICTED_BOUNDS
    );
}
