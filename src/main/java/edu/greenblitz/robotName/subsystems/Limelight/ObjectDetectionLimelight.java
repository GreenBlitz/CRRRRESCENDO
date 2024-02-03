package edu.greenblitz.robotName.subsystems.Limelight;

import edu.greenblitz.robotName.VisionConstants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class ObjectDetectionLimelight {
	private NetworkTableEntry notePositionEntry;
	private static ObjectDetectionLimelight instance;

	public ObjectDetectionLimelight(){
		String name = VisionConstants.OBJECT_DETECTION_LIMELIGHT_NAME;

		notePositionEntry = NetworkTableInstance.getDefault().getTable(name).getEntry("");
	}

	public static ObjectDetectionLimelight getInstance() {
		return instance;
	}

	public static void init(){
		if (instance == null) {
			instance = new ObjectDetectionLimelight();
		}
	}

	public Pose2d getObjectPosition(){
		double[] poseArray = notePositionEntry.getDoubleArray(new double[VisionConstants.OBJECT_DETECTION_LIMELIGHT_ENTRY_ARRAY_LENGTH]);

		double xPosition = poseArray[VisionConstants.getValue(VisionConstants.LIMELIGHT_ARRAY_VALUES.X_AXIS)];

		double yPosition = poseArray[VisionConstants.getValue(VisionConstants.LIMELIGHT_ARRAY_VALUES.Y_AXIS)];

		return new Pose2d(new Translation2d(xPosition,yPosition), new Rotation2d());
	}
}
