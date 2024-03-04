package edu.greenblitz.robotName.subsystems.shooter.pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.math.interpolation.InterpolatingTreeMap;

public class PivotConstants {

	public static final double SHOOTER_MASS_KG = 3;

	public static final double LENGTH_OF_SHOOTER = 0.418;
	
	public enum PresetPositions {

		STARTING(Rotation2d.fromDegrees(18)),
		TRANSFER(Rotation2d.fromDegrees(20)),
		SAFE(Rotation2d.fromDegrees(58)),
		PICK_UP(Rotation2d.fromDegrees(25)),
		CLOSE_SHOOTING(Rotation2d.fromDegrees(60)),
		PODIUM(Rotation2d.fromDegrees(36.3)),
		RIGHT_STAGE(Rotation2d.fromDegrees(29)),
		FEEDER(Rotation2d.fromDegrees(58.9));

		public final Rotation2d ANGLE;

		PresetPositions(Rotation2d angle) {
			this.ANGLE = angle;
		}

	}
	
	public static InterpolatingTreeMap<Double, Double> pivotAngleByDistance = new InterpolatingDoubleTreeMap();
	static {
		pivotAngleByDistance.put(
				1.0, 60.0
		);
		pivotAngleByDistance.put(
				3.47, 36.3
		);
		pivotAngleByDistance.put(
				5.39, 29.0
		);
	}
	
	
	public static final Rotation2d BACKWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(20);

	public static final Rotation2d CLOSE_RANGE_SHOOTING_ANGLE = Rotation2d.fromDegrees(113.8);

	public static final Rotation2d FORWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(67);

	public static final double GEAR_RATIO = 15 * (72 / 14.0) * 2;

	public static final Translation3d ROBOT_RELATIVE_PIVOT_POSITION = new Translation3d(-0.1, 0, 0.21);

	public static final Rotation2d TOLERANCE = Rotation2d.fromDegrees(0.75);
}