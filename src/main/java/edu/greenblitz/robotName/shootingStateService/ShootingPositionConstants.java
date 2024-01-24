package edu.greenblitz.robotName.shootingStateService;

import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Translation2d;

import java.util.ArrayList;
import java.util.List;

public class ShootingPositionConstants {
    public static final Translation2d CENTER_OF_CIRCLE = new Translation2d(69, 69);
    public static final double CIRCLE_RADIUS_IN_METERS = 69;
    public static ShootingZone ZONE = new ShootingZone(
            CENTER_OF_CIRCLE,
            CIRCLE_RADIUS_IN_METERS
    );

    public static final Pair<Translation2d, Translation2d> ENEMY_FEEDER = new Pair<>(
            new Translation2d(0, 0),
            new Translation2d(1, 1)
    );
    public static final Pair<Translation2d, Translation2d> STAGE = new Pair<>(
            new Translation2d(3, 3),
            new Translation2d(1, 1)
    );

    public static final List<Pair<Translation2d, Translation2d>> FORBIDDEN_BOUNDS = new ArrayList<>();

    static {
        FORBIDDEN_BOUNDS.add(ENEMY_FEEDER);
        FORBIDDEN_BOUNDS.add(STAGE);

        ZONE.setRestrictedBounds(FORBIDDEN_BOUNDS);
    }
}
