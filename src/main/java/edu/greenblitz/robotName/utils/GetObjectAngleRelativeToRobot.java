package edu.greenblitz.robotName.utils;

import edu.greenblitz.robotName.subsystems.Limelight.MultiLimelight;
import edu.greenblitz.robotName.subsystems.Limelight.ObjectDetectionLimelight;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Pose2d;

import java.util.function.DoubleSupplier;

public class GetObjectAngleRelativeToRobot{
	public static double getObjectAngle() {
		Pair<Double,Double> objectPosition = ObjectDetectionLimelight.getInstance().getObjectPosition();
		return Math.atan2(objectPosition.getSecond(),objectPosition.getFirst());
	}
}
