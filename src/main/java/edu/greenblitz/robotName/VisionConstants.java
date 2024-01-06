package edu.greenblitz.robotName;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;

public class VisionConstants {
	public static final String[] LIMELIGHT_NAMES = new String[]{"limelight-front"};
	public static double STANDARD_DEVIATION_ODOMETRY = 0.001;
	public static double STANDARD_DEVIATION_VISION2D = 0.3;
	public static double STANDARD_DEVIATION_VISION_ANGLE = 0.6;
	public static final int[] PORT_NUMBERS = {5800, 5801, 5802, 5803, 5804, 5805};
	public static final Transform3d ROBOT_TO_CAMERA = new Transform3d(new Translation3d(), new Rotation3d());
	public final static double MIN_DISTANCE_TO_FILTER_OUT = 1.5;
	public final static double VISION_CONSTANT = 0.2;
	
	
}
