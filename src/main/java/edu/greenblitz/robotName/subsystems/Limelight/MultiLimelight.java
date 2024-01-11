package edu.greenblitz.robotName.subsystems.Limelight;

import edu.greenblitz.robotName.VisionConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MultiLimelight extends GBSubsystem {

	private static MultiLimelight instance;
	private List<Limelight> limelights;


	private MultiLimelight(){
		limelights = new ArrayList<>();
		for(String limelightName : VisionConstants.LIMELIGHT_NAMES){
			limelights.add(new Limelight(limelightName));
		}
	}


	public static MultiLimelight getInstance() {
		if (instance == null) {
			init();
			SmartDashboard.putBoolean("limelight initialized via getinstance", true);
		}
		return instance;
	}

	public static void init(){
		instance = new MultiLimelight();
	}

	public List<Optional<Pair<Pose2d, Double>>> getAllEstimates(){
		ArrayList<Optional<Pair<Pose2d, Double>>> estimates = new ArrayList<>();
		for (Limelight limelight : limelights){
			limelight.getUpdatedPoseEstimation();
			estimates.add(limelight.getUpdatedPoseEstimation());
		}
		return estimates;
	}

	public Pose2d getAveragePos() {
		double avgX = 0;
		double avgY = 0;
		double avgRotation = 0;
		for(Limelight limelight : limelights) {
			if(limelight.getUpdatedPoseEstimation().isPresent()) {
				avgX += limelight.getUpdatedPoseEstimation().get().getFirst().getX();
				avgY += limelight.getUpdatedPoseEstimation().get().getFirst().getY();
				avgRotation += limelight.getUpdatedPoseEstimation().get().getFirst().getRotation().getRadians();
			}
		}
		avgX /= limelights.size();
		avgY /= limelights.size();
		avgRotation /= limelights.size();
//		System.out.println("amirrimon");
//		System.out.println("(" + avgX + "," + avgY + ") - " + Math.toDegrees(avgRotation));
		return new Pose2d(new Translation2d(avgX,avgY),new Rotation2d(avgRotation));
	}
	
	public boolean isConnected(int limelightId){
		return limelights.get(limelightId).hasTarget();
	}


	public Optional<Pair<Pose2d, Double>> getFirstAvailableTarget(){
		for(Optional<Pair<Pose2d, Double>> output : getAllEstimates()){
			if (output.isPresent()){
				return output;
			}
		}
		return Optional.empty();
	}
}
