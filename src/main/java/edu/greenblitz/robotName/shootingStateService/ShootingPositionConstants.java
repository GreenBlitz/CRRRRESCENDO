package edu.greenblitz.robotName.shootingStateService;

import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Translation2d;

import java.util.ArrayList;
import java.util.List;

public class ShootingPositionConstants {
    public static final Translation2d CENTER_OF_CIRCLE = new Translation2d(69, 69);
    public static final double CIRCLE_RADIUS_IN_METERS = 69;
    public static ShootingZone LEGAL_SHOOTING_ZONE = new ShootingZone(CENTER_OF_CIRCLE, CIRCLE_RADIUS_IN_METERS);


    private class ShootingBounds {
        public static final List<Pair<Translation2d, Translation2d>> FORBIDDEN_BOUNDS = new ArrayList<>();

        public static final Pair<Translation2d, Translation2d> ENEMY_FEEDER = new Pair<>(
                new Translation2d(0, 0),
                new Translation2d(1, 1)
        );
        public static final Pair<Translation2d, Translation2d> STAGE = new Pair<>(
                new Translation2d(3, 3),
                new Translation2d(1, 1)
        );
    }


    static {
        ShootingBounds.FORBIDDEN_BOUNDS.add(ShootingBounds.ENEMY_FEEDER);
        ShootingBounds.FORBIDDEN_BOUNDS.add(ShootingBounds.STAGE);

        LEGAL_SHOOTING_ZONE.setRestrictedBounds(ShootingBounds.FORBIDDEN_BOUNDS);
    }
}
