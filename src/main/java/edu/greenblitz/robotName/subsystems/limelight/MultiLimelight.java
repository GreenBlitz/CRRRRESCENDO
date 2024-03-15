package edu.greenblitz.robotName.subsystems.limelight;

import edu.greenblitz.robotName.VisionConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Pose2d;
import org.littletonrobotics.junction.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MultiLimelight extends GBSubsystem {

	private static MultiLimelight instance;

	private List<Limelight> limelights;

	private MultiLimelight() {
		limelights = new ArrayList<>();
		for (String limelightName : VisionConstants.LIMELIGHT_NAMES) {
			limelights.add(new Limelight(limelightName));
		}
	}

	public static void init() {
		instance = new MultiLimelight();
	}

	public static MultiLimelight getInstance() {
		if (instance == null) {
			init();
		}
		return instance;
	}

	public List<Optional<Pair<Pose2d, Double>>> getAll2DEstimates() {
		ArrayList<Optional<Pair<Pose2d, Double>>> estimates = new ArrayList<>();
		for (Limelight limelight : limelights) {
			if (limelight.hasTarget()) {
					estimates.add(limelight.getUpdatedPose2DEstimation());
			}
		}
		return estimates;
	}

	public void recordEstimatedPositions() {
		int i = 0;
		for (Optional<Pair<Pose2d, Double>> estimation : MultiLimelight.getInstance().getAll2DEstimates()) {
			if (estimation.isPresent()) {
				Logger.recordOutput("estimation " + i, estimation.get().getFirst());
			}
		}
	}

	public double getDynamicStdDevs(int limelightId) {
		return limelights.get(limelightId).getDistanceFromTag() / VisionConstants.VISION_TO_STANDARD_DEVIATION;
	}

	public boolean hasTarget(int limelightId) {
		return limelights.get(limelightId).hasTarget();
	}

	public Optional<Pair<Pose2d, Double>> getFirstAvailableTarget() {
		for (Optional<Pair<Pose2d, Double>> output : getAll2DEstimates()) {
			if (output.isPresent()) {
				return output;
			}
		}
		return Optional.empty();
	}

	public boolean isConnected() {
		return limelights.get(0).hasTarget();
	}
}