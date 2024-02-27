package edu.greenblitz.robotName.subsystems.swerve;

public class SwerveChassisUtils {
 
	public static double joystickValueToOutputValue(double joystickValue, double velocityFactor, boolean isInverted) {
		double factoredOutputValue = joystickValue * velocityFactor;
		if (Math.min(Math.abs(factoredOutputValue), velocityFactor) == velocityFactor){
			return velocityFactor * Math.signum(joystickValue) * (isInverted ? -1 : 1);
		}
		return factoredOutputValue * (isInverted ? -1 : 1);
	}
}
