package edu.greenblitz.robotName.subsystems.Limelight;

import edu.greenblitz.robotName.VisionConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
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
		}
		return instance;
	}

	public static void init(){
		instance = new MultiLimelight();
	}

	public List<Optional<Pair<Pose2d, Double>>> getAll2DEstimates(){
		ArrayList<Optional<Pair<Pose2d, Double>>> estimates = new ArrayList<>();
		for (Limelight limelight : limelights){
			if (limelight.hasTarget() && Math.abs(VisionConstants.APRIL_TAG_HEIGHT - limelight.getTagHeight()) < VisionConstants.APRIL_TAG_HEIGHT_TOLERANCE) {
				estimates.add(limelight.getUpdatedPose2DEstimation());
			}
		}
		return estimates;
	}
	
	public double getDynamicStdDevs(int limelightId){
		double stdDevsSum = limelights.get(limelightId).getDistanceFromTag() / VisionConstants.VisionToStdDevs;
		return stdDevsSum;
	}
	

	
	

	
	public boolean isConnected(int limelightId){
		return limelights.get(limelightId).hasTarget();
	}
	
	
	public Optional<Pair<Pose2d, Double>> getFirstAvailableTarget(){
		for(Optional<Pair<Pose2d, Double>> output : getAll2DEstimates()){
			if (output.isPresent()){
				return output;
			}
		}
		return Optional.empty();
	}
}
