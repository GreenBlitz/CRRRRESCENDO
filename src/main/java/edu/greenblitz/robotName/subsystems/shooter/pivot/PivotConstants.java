package edu.greenblitz.robotName.subsystems.shooter.pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.math.interpolation.InterpolatingTreeMap;

public class PivotConstants {

	public static final double SHOOTER_MASS_KG = 3;

	public static final double LENGTH_OF_SHOOTER = 0.418;
	
	public enum PresetPositions {

		STARTING(Rotation2d.fromDegrees(16)),
		TRANSFER(Rotation2d.fromDegrees(18)),
		SAFE(Rotation2d.fromDegrees(56)),
		PICK_UP(Rotation2d.fromDegrees(38)),
		CLOSE_SHOOTING(Rotation2d.fromDegrees(58)),
		PODIUM(Rotation2d.fromDegrees(34.3)),
		RIGHT_STAGE(Rotation2d.fromDegrees(27)),
		FEEDER(Rotation2d.fromDegrees(56.9));

		public final Rotation2d ANGLE;

		PresetPositions(Rotation2d angle) {
			this.ANGLE = angle;
		}

	}
	
	public static final Rotation2d BACKWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(16.5);
	
	public static final Rotation2d FORWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(67);

	public static final double GEAR_RATIO = 15 * (72 / 14.0) * 2;

	public static final Translation3d ROBOT_RELATIVE_PIVOT_POSITION = new Translation3d(-0.1, 0, 0.21);

	public static final Rotation2d TOLERANCE = Rotation2d.fromDegrees(0.5);
}