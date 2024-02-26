package edu.greenblitz.robotName.subsystems.swerve;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import org.opencv.core.Mat;

public class SwerveChassisUtils {
 
	public static double joystickValueToOutputValue(double joystickValue, double velocityFactor, boolean isInverted) {
		double factoredOutputValue = joystickValue * velocityFactor;
		factoredOutputValue =  Math.min(Math.abs(factoredOutputValue), velocityFactor);
		return factoredOutputValue *  (isInverted ? -1 : 1);
	}
}
