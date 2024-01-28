package edu.greenblitz.robotName.shootingStateService;

import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.math.geometry.Translation2d;

public class ShootingPositionConstants {
    public static final Translation2d CENTER_OF_CIRCLE = new Translation2d(0, 0);
    public static final double CIRCLE_RADIUS_IN_METERS = 3;
    public static ShootingZone LEGAL_SHOOTING_ZONE = new ShootingZone(
            CENTER_OF_CIRCLE,
            CIRCLE_RADIUS_IN_METERS,
            FieldConstants.ShootingZoneConstants.RESTRICTED_BOUNDS
    );
}
