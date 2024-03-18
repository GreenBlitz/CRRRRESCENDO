package edu.greenblitz.robotName;


import edu.greenblitz.robotName.utils.FMSUtils;
import edu.greenblitz.robotName.utils.shootingCalculations.Bound;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import org.littletonrobotics.junction.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * All constants in this file are measured in meters.
 * <p>
 * When talking about 3d objects the z direction is up.
 */
public class FieldConstants {

    public final static double FIELD_LENGTH = 16.54175;

    public final static double FIELD_WIDTH = 8.0137;

    public final static Rotation2d RED_AMP_ANGLE = Rotation2d.fromDegrees(270);

    public final static Rotation2d BLUE_AMP_ANGLE = Rotation2d.fromDegrees(270);

    public static final Translation3d MIDDLE_OF_BLUE_SPEAKER_POSITION = new Translation3d(
            0.23,
            FIELD_WIDTH - 2.55,
            2.045
    );

    public static final Translation3d MIDDLE_OF_RED_SPEAKER_POSITION = new Translation3d(
            FIELD_LENGTH - MIDDLE_OF_BLUE_SPEAKER_POSITION.getX(),
            MIDDLE_OF_BLUE_SPEAKER_POSITION.getY(),
            MIDDLE_OF_BLUE_SPEAKER_POSITION.getZ()
    );

    public static final Translation3d MIDDLE_OF_SPEAKER_POSITION = FMSUtils.isRedAlliance() ? MIDDLE_OF_RED_SPEAKER_POSITION : MIDDLE_OF_BLUE_SPEAKER_POSITION;

    public static class FieldZones {

        public static final List<Bound> RESTRICTED_BOUNDS = new ArrayList<>();

        public static final Bound RED_STAGE_ZONE = new Bound(
                new Translation2d(13.8, 3),
                new Translation2d(12.4, 4.95)
        );

        public static final Bound BLUE_STAGE_ZONE = new Bound(
                new Translation2d(13.8, 3),
                new Translation2d(12.4, 4.95)
        );

        public static final Bound RED_FEEDER_ZONE = new Bound(
                new Translation2d(12.5, 7.4),
                new Translation2d(15, 15)
        );

        public static final Bound BLUE_FEEDER_ZONE = new Bound(
                new Translation2d(12.5, 7.4),
                new Translation2d(15, 15)
        );

        public static final Bound ENEMY_FEEDER_ZONE = FMSUtils.isRedAlliance() ? BLUE_FEEDER_ZONE : RED_FEEDER_ZONE;

        public static final Bound STAGE_ZONE = FMSUtils.isRedAlliance() ? RED_STAGE_ZONE : BLUE_STAGE_ZONE;

        static {
            RESTRICTED_BOUNDS.add(STAGE_ZONE);
            RESTRICTED_BOUNDS.add(ENEMY_FEEDER_ZONE);
        }

        static {
            Logger.recordOutput("Field/selected speaker pose", MIDDLE_OF_SPEAKER_POSITION.toTranslation2d());
        }
    }


}