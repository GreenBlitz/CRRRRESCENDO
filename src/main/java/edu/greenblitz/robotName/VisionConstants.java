package edu.greenblitz.robotName;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;

public class VisionConstants {
	public static final String[] LIMELIGHT_NAMES = new String[]{"limelight-front","limelight-back"};
	public static final String OBJECT_DETECTION_LIMELIGHT_NAME = "limelight-gb";
	public static double STANDARD_DEVIATION_ODOMETRY = 1;
	public static int LIMELIGHT_ENTRY_ARRAY_LENGTH = 7;
	public static int OBJECT_DETECTION_LIMELIGHT_ENTRY_ARRAY_LENGTH = 7;
	public static double STANDARD_DEVIATION_ODOMETRY_ANGLE = 0.01;
	public static double STANDARD_DEVIATION_VISION2D = 0.5;
	public static double STANDARD_DEVIATION_VISION_ANGLE = 0.6;
	public static final int[] PORT_NUMBERS = {5800, 5801, 5802, 5803, 5804, 5805};
	public static final Transform3d ROBOT_TO_CAMERA = new Transform3d(new Translation3d(), new Rotation3d());
	public final static double MIN_DISTANCE_TO_FILTER_OUT = 5;//meters
	public final static double VISION_TO_STANDARD_DEVIATION = 10;
	public final static double APRIL_TAG_HEIGHT = 0.18;//cm
	public final static double APRIL_TAG_HEIGHT_TOLERANCE = 0.04;//cm
	public final static int APRILTAG_PIPELINE = 0;
	public final static int OBJECT_DETECTION_PIPELINE = 1;
	
	public static enum LIMELIGHT_ARRAY_VALUES{
		X_AXIS,
		Y_AXIS,
		Z_AXIS,
		ROLL_ANGLE,
		PITCH_ANGLE,
		YAW_ANGLE,
		TOTAL_LATENCY ;
	}

	public static int getValue(LIMELIGHT_ARRAY_VALUES value) {
		int arrayValue = -1;
		switch (value){
			case X_AXIS -> {
				arrayValue = 0;
			}
			case Y_AXIS -> {
				arrayValue = 1;
			}
			case Z_AXIS -> {
				arrayValue = 2;
			}
			case ROLL_ANGLE -> {
				arrayValue = 3;
			}
			case YAW_ANGLE -> {
				arrayValue = 4;
			}
			case PITCH_ANGLE -> {
				arrayValue = 5;
			}
			case TOTAL_LATENCY -> {
				arrayValue = 6;
			}
		}
		return arrayValue;
	}
}
