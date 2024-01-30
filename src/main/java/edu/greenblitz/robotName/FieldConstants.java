package edu.greenblitz.robotName;


import edu.greenblitz.robotName.utils.FMSUtils;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.DriverStation;

import java.util.ArrayList;
import java.util.List;

/**
 * All constants in this file are measured in meters.
 * <p>
 * When talking about 3d objects the z direction is up.
 */
public class FieldConstants { //need to find
    public final static double FIELD_LENGTH = 16.54175;

    public final static double FIELD_WIDTH = 8.0137;

    public static final Translation3d MIDDLE_OF_RED_SPEAKER_POSITION = new Translation3d(15.24, 5.9944, 2.045);

    public static final Translation3d MIDDLE_OF_BLUE_SPEAKER_POSITION = new Translation3d(15.24, 5.9944, 2.045);

    public static final Translation3d MIDDLE_OF_SPEAKER_POSITION = FMSUtils.getAlliance() == DriverStation.Alliance.Red ? MIDDLE_OF_RED_SPEAKER_POSITION : MIDDLE_OF_BLUE_SPEAKER_POSITION;

    public static class ShootingZoneConstants {

        public static final List<Pair<Translation2d, Translation2d>> RESTRICTED_BOUNDS = new ArrayList<>();

        public static final Pair<Translation2d, Translation2d> RED_STAGE_ZONE = new Pair<>(
                new Translation2d(13.8, 3),
                new Translation2d(12.4, 4.95)
        );

        public static final Pair<Translation2d, Translation2d> BLUE_STAGE_ZONE = new Pair<>(
                new Translation2d(13.8, 3),
                new Translation2d(12.4, 4.95)
        );

        public static final Pair<Translation2d, Translation2d> STAGE_ZONE = FMSUtils.getAlliance() == DriverStation.Alliance.Red ? RED_STAGE_ZONE : BLUE_STAGE_ZONE;

        public static final Pair<Translation2d, Translation2d> BLUE_FEEDER_ZONE = new Pair<>(
                new Translation2d(12.5, 7.4),
                new Translation2d(15, 15)
        );

        public static final Pair<Translation2d, Translation2d> RED_FEEDER_ZONE = new Pair<>(
                new Translation2d(12.5, 7.4),
                new Translation2d(15, 15)
        );

        public static final Pair<Translation2d, Translation2d> ENEMY_FEEDER = FMSUtils.getAlliance() == DriverStation.Alliance.Red ? BLUE_FEEDER_ZONE : RED_FEEDER_ZONE;

        static {
            RESTRICTED_BOUNDS.add(STAGE_ZONE);
            RESTRICTED_BOUNDS.add(ENEMY_FEEDER);
        }
    }
}
