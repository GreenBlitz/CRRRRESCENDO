package edu.greenblitz.robotName;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class Field {

	/**
	 * gets pose, returns it fitted to the other alliance ("mirrored") and rotated by 180 degrees.
	 */
	public static final boolean IS_AT_HADREAM_MIRRORD = false;

	public static Pose2d mirrorPositionToOtherSide(Pose2d pose) {
		Pose2d mirroredPose = new Pose2d(
				FieldConstants.FIELD_LENGTH - pose.getX(),
				pose.getY(),
				Rotation2d.fromRadians(Math.PI).minus(pose.getRotation())
		);
		return mirroredPose;
	}

	public static Pose2d mirrorPositionToOtherSideWithoutAngle(Pose2d pose) {
		Pose2d mirroredPose = new Pose2d(
				FieldConstants.FIELD_LENGTH - pose.getX(),
				pose.getY(),
				pose.getRotation()
		);
		return mirroredPose;
	}

	/**
	 * gets pose[], returns it fitted to the other alliance ("mirrored") and rotated by 180 degrees.
	 */
	public static Pose2d[] mirrorPositionsToOtherSide(Pose2d[] poses) {
		Pose2d[] mirroredPoses = new Pose2d[poses.length];
		for (int i = 0; i < poses.length; i++) {
			mirroredPoses[i] = mirrorPositionToOtherSide(poses[i]);
		}
		return mirroredPoses;
	}

	public static class Apriltags {

		public static final AprilTagFieldLayout APRIL_TAG_FIELD_LAYOUT;

		static {
			try {
				APRIL_TAG_FIELD_LAYOUT = AprilTagFieldLayout.loadFromResource(AprilTagFields.k2024Crescendo.m_resourceFile);
			} catch (IOException ioException) {
				throw new RuntimeException(ioException);
			}
		}
	}

	public static class PlacementLocations {

		// the locations of the grid plus half of the robot length.
		private static final Pose2d[] LOCATIONS_ON_BLUE_SIDE = {
				new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Syncopa.ROBOT_LENGTH_IN_METERS) + RobotConstants.Syncopa.BUMPER_LENGTH + 0.45, 0.508 + 0.36), new Rotation2d(Math.PI)),
				new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Syncopa.ROBOT_LENGTH_IN_METERS) + RobotConstants.Syncopa.BUMPER_LENGTH + 0.45, 1.067 + 0.36), new Rotation2d(Math.PI)),
				new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Syncopa.ROBOT_LENGTH_IN_METERS) + RobotConstants.Syncopa.BUMPER_LENGTH + 0.45, 1.626 + 0.36), new Rotation2d(Math.PI)),
				new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Syncopa.ROBOT_LENGTH_IN_METERS) + RobotConstants.Syncopa.BUMPER_LENGTH + 0.45, 2.184 + 0.36), new Rotation2d(Math.PI)),
				new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Syncopa.ROBOT_LENGTH_IN_METERS) + RobotConstants.Syncopa.BUMPER_LENGTH + 0.45, 2.743 + 0.36), new Rotation2d(Math.PI)),
				new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Syncopa.ROBOT_LENGTH_IN_METERS) + RobotConstants.Syncopa.BUMPER_LENGTH + 0.45, 3.302 + 0.36), new Rotation2d(Math.PI)),
				new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Syncopa.ROBOT_LENGTH_IN_METERS) + RobotConstants.Syncopa.BUMPER_LENGTH + 0.45, 3.861 + 0.36), new Rotation2d(Math.PI)),
				new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Syncopa.ROBOT_LENGTH_IN_METERS) + RobotConstants.Syncopa.BUMPER_LENGTH + 0.45, 4.420 + 0.36), new Rotation2d(Math.PI)),
				new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Syncopa.ROBOT_LENGTH_IN_METERS) + RobotConstants.Syncopa.BUMPER_LENGTH + 0.45, 4.978 + 0.36), new Rotation2d(Math.PI))
		};

		public static Pose2d[] getLocationsOnBlueSide() {
			return LOCATIONS_ON_BLUE_SIDE;
		}

		public static Pose2d[] getLocationsOnRedSide() {
			return mirrorPositionsToOtherSide(LOCATIONS_ON_BLUE_SIDE);
		}

		private static final HashSet<Integer> CUBE_INDICES = new HashSet<>(Arrays.asList(1, 4, 7));

		public static boolean isGridPositionIDofCube(int index) {
			return CUBE_INDICES.contains(index);
		}

		public static boolean isGridPositionIDofCone(int index) {
			return !isGridPositionIDofCube(index);
		}

		public static final Pose2d OUT_PRE_BALANCE_BLUE = new Pose2d(5.40, 2.67, new Rotation2d(Math.PI));
	}

	public static class ScoringPositions {

		public final static Pose2d RED_SPEAKER_SCORE_POSITION = new Pose2d(new Translation2d(14.140, 5.85), new Rotation2d());

		public final static Pose2d RED_SCORE_POSITION = new Pose2d(new Translation2d(14.85, 7.5), new Rotation2d());

		public final static Rotation2d RED_MID_CLIMB_ANGLE = Rotation2d.fromDegrees(180);

		public final static Rotation2d RED_SOURCE_CLIMB_ANGLE = Rotation2d.fromDegrees(-60);

		public final static Rotation2d RED_AMP_CLIMB_ANGLE =Rotation2d.fromDegrees(60);

		public final static Rotation2d BLUE_MID_CLIMB_ANGLE = RED_MID_CLIMB_ANGLE.minus(Rotation2d.fromDegrees(180));

		public final static Rotation2d BLUE_SOURCE_CLIMB_ANGLE = RED_SOURCE_CLIMB_ANGLE.minus(Rotation2d.fromDegrees(60));

		public final static Rotation2d BLUE_AMP_CLIMB_ANGLE = RED_AMP_CLIMB_ANGLE.plus(Rotation2d.fromDegrees(60));

		public final static Pose2d RED_AMP_CLIMB_POSITION = new Pose2d(new Translation2d(12.2, 5), RED_AMP_CLIMB_ANGLE);

		public final static Pose2d RED_SOURCE_CLIMB_POSITION = new Pose2d(new Translation2d(12.2, 3.2), RED_SOURCE_CLIMB_ANGLE);

		public final static Pose2d RED_MID_CLIMB_POSITION = new Pose2d(new Translation2d(10.6, 4), RED_MID_CLIMB_ANGLE);

		public final static Pose2d BLUE_SCORE_POSITION = mirrorPositionToOtherSide(RED_SCORE_POSITION);

		public final static Pose2d BLUE_AMP_CLIMB_POSITION = new Pose2d(mirrorPositionToOtherSide(RED_AMP_CLIMB_POSITION).getTranslation(), BLUE_AMP_CLIMB_ANGLE);

		public final static Pose2d BLUE_SOURCE_CLIMB_POSITION = new Pose2d(mirrorPositionToOtherSide(RED_SOURCE_CLIMB_POSITION).getTranslation(), BLUE_SOURCE_CLIMB_ANGLE);

		public final static Pose2d BLUE_MID_CLIMB_POSITION = new Pose2d(mirrorPositionToOtherSide(RED_MID_CLIMB_POSITION).getTranslation(), BLUE_MID_CLIMB_ANGLE);

		public final static Pose2d RED_PRE_AMP_SCORE_POSITION = new Pose2d(new Translation2d(14.85, 7.3), new Rotation2d());

		public final static Pose2d RED_AMP_SCORE_POSITION = new Pose2d(new Translation2d(14.85, 7.5), Rotation2d.fromDegrees(90));

		public final static Pose2d BLUE_SPEAKER_SCORE_POSITION = mirrorPositionToOtherSide(RED_SPEAKER_SCORE_POSITION);

		public final static Pose2d BLUE_PRE_AMP_SCORE_POSITION = mirrorPositionToOtherSide(RED_PRE_AMP_SCORE_POSITION);

		public final static Pose2d BLUE_AMP_SCORE_POSITION = mirrorPositionToOtherSideWithoutAngle(RED_AMP_SCORE_POSITION);
	}

	public static class UpStairsFieldPositions {

		public final static Pose2d SPEAKER_SCORE_POSITION = new Pose2d(new Translation2d(), new Rotation2d());

		public final static Pose2d AMP_SCORE_POSITION = new Pose2d(new Translation2d(), new Rotation2d());

		public final static Pose2d CLIMB_POSITION = new Pose2d(new Translation2d(), new Rotation2d());
	}
}
