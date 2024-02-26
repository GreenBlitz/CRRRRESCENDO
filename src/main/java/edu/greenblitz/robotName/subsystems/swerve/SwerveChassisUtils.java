package edu.greenblitz.robotName.subsystems.swerve;

public class SwerveChassisUtils {
 
	public static double joystickValueToOutputValue(double joystickValue, double velocityFactor, boolean isInverted) {
		double factoredOutputValue = joystickValue * velocityFactor;
		double a = Math.min(factoredOutputValue, velocityFactor) * (isInverted ? -1 : 1);
		if (Math.abs(a) == velocityFactor){
			a *= Math.signum(joystickValue);
		}
		return a;
	}
}
