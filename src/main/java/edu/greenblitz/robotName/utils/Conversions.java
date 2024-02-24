package edu.greenblitz.robotName.utils;


import edu.greenblitz.robotName.subsystems.arm.wrist.srxWrist.SRXWristConstants;
import edu.greenblitz.robotName.subsystems.swerve.modules.mk4iSwerveModule.MK4iSwerveConstants;
import edu.wpi.first.math.geometry.Rotation2d;


public class Conversions {
	
	public static double convertRPMToRadiansPerSec(double rpm) {
		return rpm * 2 * Math.PI / 60;
	}
	
	public static double convertRPMToMeterPerSecond(double rpm, double wheelRadius) {
		return (2 * Math.PI * wheelRadius) / 60 * rpm;
	}
	
	public static class MK4IConversions {
		public static double convertRevolutionToMeters(double revolutions) {
			return revolutions * MK4iSwerveConstants.WHEEL_CIRCUMFERENCE * MK4iSwerveConstants.LINEAR_GEAR_RATIO;
		}
		
		public static double convertRadiansToRotations(Rotation2d angle) {
			return angle.getRadians() / MK4iSwerveConstants.ANGLE_REVOLUTIONS_TO_RADIANS;
		}
		
		public static double convertRevolutionsToRadians(double revolutions) {
			return revolutions * MK4iSwerveConstants.ANGLE_REVOLUTIONS_TO_RADIANS;
		}
		
		public static double convertMetersToTicks(double distanceInMeters) {
			return distanceInMeters / MK4iSwerveConstants.LINEAR_REVOLUTIONS_TO_METERS;
		}
		
		public static double convertRevolutionsToMeters(double angleInRevolutions) {
			return angleInRevolutions * MK4iSwerveConstants.LINEAR_REVOLUTIONS_TO_METERS;
		}
		
		public static double convertRPMToMeterPerSecond(double rpm) {
			return Conversions.convertRPMToMeterPerSecond(rpm, MK4iSwerveConstants.WHEEL_CIRCUMFERENCE / (2 * Math.PI));
		}
	}

	public static class MagEncoderConversions {

		public static Rotation2d MotorPositionToRotation2D(double motorPosition) {
			return Rotation2d.fromRotations(motorPosition / SRXWristConstants.MAG_ENCODER_CONVERSION_FACTOR);
		}

		public static double Rotation2DToMotorPosition(Rotation2d angle) {
			return angle.getRotations() * SRXWristConstants.MAG_ENCODER_CONVERSION_FACTOR;
		}
	}
}
