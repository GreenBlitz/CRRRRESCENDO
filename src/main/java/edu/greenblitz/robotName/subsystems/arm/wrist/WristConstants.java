package edu.greenblitz.robotName.subsystems.arm.wrist;

import edu.wpi.first.math.geometry.Rotation2d;

public class WristConstants {

	public enum PresetPositions {

		STARTING(Rotation2d.fromDegrees(90)),
		SCORE(Rotation2d.fromDegrees(190)),
		SCORE_TRAP(Rotation2d.fromDegrees(390)), // find
		TRANSFER(Rotation2d.fromDegrees(185)),
		SAFE(Rotation2d.fromDegrees(180));

		public final Rotation2d ANGLE;

		PresetPositions(Rotation2d angle) {
			this.ANGLE = angle;
		}
	}

	public static final double LENGTH_OF_ENDEFFECTOR = 0.1;

	public static final double WRIST_MASS_KG = 0.5;

	public static final Rotation2d BACKWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(-50);

	public static final Rotation2d FORWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(200);

	public static final double CONVERSION_FACTOR = 1;

	public static final Rotation2d TOLERANCE = Rotation2d.fromDegrees(10);

	public static final int CURRENT_LIMIT = 40;
}