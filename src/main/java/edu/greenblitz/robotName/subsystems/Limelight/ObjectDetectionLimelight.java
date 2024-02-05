package edu.greenblitz.robotName.subsystems.Limelight;

import edu.greenblitz.robotName.VisionConstants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class ObjectDetectionLimelight {
	private NetworkTableEntry xNotePositionEntry, yNotePositionEntry, confidenceNotePositionEntry;
	private static ObjectDetectionLimelight instance;

	public ObjectDetectionLimelight(){
		String name = VisionConstants.OBJECT_DETECTION_LIMELIGHT_NAME;

		xNotePositionEntry = NetworkTableInstance.getDefault().getTable(name).getEntry("json").getInstance().getTable("Detector").getInstance().getEntry("tx");
		yNotePositionEntry = NetworkTableInstance.getDefault().getTable(name).getEntry("json").getInstance().getTable("Detector").getInstance().getEntry("ty");
		confidenceNotePositionEntry = NetworkTableInstance.getDefault().getTable(name).getEntry("json").getInstance().getEntry("tx");
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
		MultiLimelight.getInstance().changePipeline(true);


		double xPosition = xNotePositionEntry.getDouble(-1);
		double yPosition = yNotePositionEntry.getDouble(-1);

		MultiLimelight.getInstance().initializeLimelightPipeline();

		return new Pose2d(new Translation2d(xPosition,yPosition), new Rotation2d());
	}

	public boolean getTargetConfidence(){

		double targetConfidence = confidenceNotePositionEntry.getDouble(-1);

		return targetConfidence >= VisionConstants.OBJECT_DETECTION_THRESHOLD;
	}
}
