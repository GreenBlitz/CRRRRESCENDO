package edu.greenblitz.robotName.subsystems.swerve;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import org.opencv.core.Mat;

public class SwerveChassisUtils {
 
	public static double joystickValueToOutputValue(double joystickValue, double velocityFactor, boolean isInverted) {
		double factoredOutputValue = joystickValue * velocityFactor;
<<<<<<< HEAD
		factoredOutputValue =  Math.min(Math.abs(factoredOutputValue), velocityFactor);
		return factoredOutputValue *  (isInverted ? -1 : 1);
=======
		double a = Math.min(factoredOutputValue, velocityFactor) * (isInverted ? -1 : 1);
		if (Math.abs(a) == velocityFactor){
			a *= Math.signum(joystickValue);
		}
		return a;
>>>>>>> edfb9acb6f695f7472b28fb25905e2f51ea279fd
	}
}
