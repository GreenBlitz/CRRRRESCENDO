package edu.greenblitz.robotName.utils;

import edu.greenblitz.robotName.subsystems.Limelight.ObjectDetectionLimelight;
import edu.wpi.first.math.geometry.Pose2d;

import java.util.function.DoubleSupplier;

public class GetObjectAngleRelativeToRobot implements DoubleSupplier {
	@Override
	public double getAsDouble() {
		Pose2d objectPosition = ObjectDetectionLimelight.getInstance().getObjectPosition();
		return Math.atan2(objectPosition.getY(),objectPosition.getX());
	}
}
