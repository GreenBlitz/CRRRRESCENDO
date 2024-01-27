package edu.greenblitz.robotName;


import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;

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

	public static final Translation3d MIDDLE_OF_SPEAKER_POSITION = new Translation3d(2.225, 0.23, 2.045);

	public static class ShootingZoneConstants {
		public static final List<Pair<Translation2d, Translation2d>> RESTRICTED_BOUNDS = new ArrayList<>();

		public static final Pair<Translation2d, Translation2d> STAGE_ZONE = new Pair<>(
				new Translation2d(1, 1),
				new Translation2d(1, 1)
		);

		public static final Pair<Translation2d, Translation2d> ENEMY_FEEDER_ZONE = new Pair<>(
				new Translation2d(1, 1),
				new Translation2d(1, 1)
		);

		static {
			RESTRICTED_BOUNDS.add(STAGE_ZONE);
			RESTRICTED_BOUNDS.add(ENEMY_FEEDER_ZONE);
		}
	}
}
