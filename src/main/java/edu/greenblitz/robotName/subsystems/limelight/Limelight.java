package edu.greenblitz.robotName.subsystems.limelight;

import edu.greenblitz.robotName.VisionConstants;
import edu.greenblitz.robotName.utils.AllianceUtilities;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;

import java.util.Optional;

public class Limelight extends GBSubsystem {

	private NetworkTableEntry robotPoseEntry, idEntry, tagPoseEntry,ledEntry;

	private String name;

	private double  counter;

	public Limelight(String limelightName) {
		this.name = limelightName;
		String robotPoseQuery =  "botpose_wpiblue";
		robotPoseEntry = NetworkTableInstance.getDefault().getTable(name).getEntry(robotPoseQuery);
		tagPoseEntry = NetworkTableInstance.getDefault().getTable(name).getEntry("targetpose_cameraspace");
		idEntry = NetworkTableInstance.getDefault().getTable(name).getEntry("tid");
		ledEntry = NetworkTableInstance.getDefault().getTable(name).getEntry("ledMode");
		counter = 0;
	}

	public Optional<Pair<Pose2d, Double>> getUpdatedPose2DEstimation() {
		double[] poseArray = robotPoseEntry.getDoubleArray(new double[VisionConstants.LIMELIGHT_ENTRY_ARRAY_LENGTH]);
		double processingLatency = poseArray[VisionConstants.getValue(VisionConstants.LIMELIGHT_ARRAY_VALUES.TOTAL_LATENCY)] / 1000;
		double timestamp = Timer.getFPGATimestamp() - processingLatency;
		int id = (int) idEntry.getInteger(-1);
		double angleOffset = 180;
		if (id == -1) {
			return Optional.empty();
		}

		Pose2d robotPose = new Pose2d(
				poseArray[VisionConstants.getValue(VisionConstants.LIMELIGHT_ARRAY_VALUES.X_AXIS)],
				poseArray[VisionConstants.getValue(VisionConstants.LIMELIGHT_ARRAY_VALUES.Y_AXIS)],
				Rotation2d.fromDegrees(poseArray[VisionConstants.getValue(VisionConstants.LIMELIGHT_ARRAY_VALUES.PITCH_ANGLE)] - angleOffset)
		);


		return Optional.of(new Pair<>(robotPose, timestamp));
	}

	public double getTagHeight() {
		double[] poseArray = tagPoseEntry.getDoubleArray(new double[VisionConstants.LIMELIGHT_ENTRY_ARRAY_LENGTH]);
		return poseArray[VisionConstants.getValue(VisionConstants.LIMELIGHT_ARRAY_VALUES.Y_AXIS)];
	}


	public boolean getTagConfidence() {
		boolean tagHeightConfidence = getDistanceFromTag() <= VisionConstants.MIN_DISTANCE_FROM_TAG_TO_DELETE;
		return tagHeightConfidence;
	}

	public double getDistanceFromTag() {
		double[] poseArray = tagPoseEntry.getDoubleArray(new double[VisionConstants.LIMELIGHT_ENTRY_ARRAY_LENGTH]);
		return poseArray[VisionConstants.getValue(VisionConstants.LIMELIGHT_ARRAY_VALUES.Z_AXIS)];
	}

	public boolean hasTarget() {
		return getUpdatedPose2DEstimation().isPresent();
	}
	
	public void turnOffLed(){
		ledEntry.setNumber(VisionConstants.LED_OFF_NETWORKTABLE_VALUE);
	}
	
	public void turnOnLed(){
		ledEntry.setNumber(VisionConstants.LED_ON_NETWORKTABLE_VALUE);
	}




	private static Pose2d lastPositionForTag4 = new Pose2d();
	private static Pose2d lastPositionForTag8 = new Pose2d();
	public boolean isSpeakerMiddleTagTrustable (int id, Pose2d currentPose){
		counter++;
		if(id == 8 || id == 7){
			if(Math.abs(lastPositionForTag8.getRotation().getDegrees() + currentPose.getRotation().getDegrees() ) <= 90){
				if (counter > 40) {
					lastPositionForTag8 = currentPose;
					counter = 0;
					return false;
				}
			}
			lastPositionForTag8 = currentPose;
			counter = 0;
			return true;
		}else if (id == 4 || id == 3){
			if(Math.abs(lastPositionForTag4.getRotation().getDegrees() + currentPose.getRotation().getDegrees() ) <= 90){
				if (counter < 40) {
					lastPositionForTag4 = currentPose;
					counter = 0;
					return false;
				}
			}
			lastPositionForTag4 = currentPose;
			counter = 0;
			return true;
		}
		return true;
	}
}