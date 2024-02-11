package edu.greenblitz.robotName.subsystems.Limelight;

import edu.greenblitz.robotName.VisionConstants;
import edu.wpi.first.math.Pair;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class ObjectDetectionLimelight {
	private NetworkTableEntry xNotePositionEntry, yNotePositionEntry, confidenceNotePositionEntry;
	private static ObjectDetectionLimelight instance;

	public ObjectDetectionLimelight(){
		String name = VisionConstants.OBJECT_DETECTION_LIMELIGHT_NAME;

		xNotePositionEntry = NetworkTableInstance.getDefault().getTable(name).getEntry("json").getInstance().getEntry("Detector").getInstance().getEntry("tx");
		yNotePositionEntry = NetworkTableInstance.getDefault().getTable(name).getEntry("json").getInstance().getEntry("Results").getInstance().getEntry("ty");
		confidenceNotePositionEntry = NetworkTableInstance.getDefault().getTable(name).getEntry("json").getInstance().getEntry("ta");
	}

	public static ObjectDetectionLimelight getInstance() {
		return instance;
	}

	public static void init(){
		if (instance == null) {
			instance = new ObjectDetectionLimelight();
		}
	}

	public Pair<Double, Double> getObjectPosition(){
		MultiLimelight.getInstance().changePipeline(true);

		double xPosition = getNoteAbsoluteAngle();

		double yPosition = Math.abs(yNotePositionEntry.getDouble(VisionConstants.DEFAULT_NETWORKTABLE_VALUE));

		MultiLimelight.getInstance().initializeLimelightPipeline();

		return new Pair<>(xPosition,yPosition);
	}

	public double getNoteAbsoluteAngle(){
		double xPosition = xNotePositionEntry.getDouble(VisionConstants.DEFAULT_NETWORKTABLE_VALUE);

		if(Math.signum(xPosition) == -1){

			xPosition = 360 - xPosition;

		}

		return xPosition;
	}

	public boolean getTargetConfidence(){

		double targetConfidence = confidenceNotePositionEntry.getDouble(VisionConstants.DEFAULT_NETWORKTABLE_VALUE);

		return targetConfidence >= VisionConstants.OBJECT_DETECTION_THRESHOLD;
	}
}
