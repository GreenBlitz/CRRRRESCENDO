package edu.greenblitz.robotName.utils;

import edu.greenblitz.robotName.subsystems.Limelight.MultiLimelight;
import edu.greenblitz.robotName.subsystems.Limelight.ObjectDetectionLimelight;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

import java.util.function.DoubleSupplier;

public class GetObjectAngleRelativeToRobot{
	public static Rotation2d getObjectAngle() {
		Pair<Double,Double> objectPosition = ObjectDetectionLimelight.getInstance().getObjectPosition();
		return new Rotation2d(Math.atan2(objectPosition.getSecond(),objectPosition.getFirst()));
	}
}
