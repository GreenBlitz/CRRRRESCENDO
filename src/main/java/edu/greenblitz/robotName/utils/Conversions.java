package edu.greenblitz.robotName.utils;


import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.Modules.mk4iSwerveModule.MK4iSwerveConstants;
import edu.wpi.first.math.geometry.Rotation2d;


public class Conversions {

    public static double convertRPMToRadiansPerSec(double rpm){
        return rpm * 2 * Math.PI / 60;
    }
    public static double convertRPMToMeterPerSecond (double rpm, double wheelRadius){
        return (2 * Math.PI * wheelRadius) / 60 * rpm;
    }

    public static class MK4IConversions{
        public static double convertRevolutionToMeters(double angInTicks){
            return angInTicks * MK4iSwerveConstants.WHEEL_CIRCUMFERENCE;
        }


        public static double revolutionsToMeters(double revolutions) {
            return revolutions * MK4iSwerveConstants.WHEEL_CIRCUMFERENCE;
        }
        public static double convertRadiansToTicks(Rotation2d angle) {
            return angle.getRadians() / MK4iSwerveConstants.ANGLE_TICKS_TO_RADIANS;
        }

        public static double convertTicksToRadians(double angInTicks) {
            return angInTicks * MK4iSwerveConstants.ANGLE_TICKS_TO_RADIANS;
        }

        public static double convertMetersToTicks(double distanceInMeters) {
            return distanceInMeters / MK4iSwerveConstants.LINEAR_TICKS_TO_METERS;
        }

        public static double convertTicksToMeters(double angInTicks){
            return angInTicks * MK4iSwerveConstants.LINEAR_TICKS_TO_METERS;
        }

        public static double convertTicksPer100msToRPM(double ticksPer100ms){
            return ticksPer100ms * RobotConstants.General.Motors.FALCON_VELOCITY_UNITS_PER_RPM;
        }

        public static double convertSensorVelocityToRPM(double ticks){
            return ticks * MK4iSwerveConstants.ANGLE_TICKS_TO_WHEEL_TO_RPM;
        }
        public static double convertSensorTicksToRadPerSecond(double ticks){
            return convertRPMToRadiansPerSec(convertSensorVelocityToRPM(ticks));
        }
        public static double convertSensorVelocityToMeterPerSecond(double selectedSensorVelocity){
            return selectedSensorVelocity * MK4iSwerveConstants.LINEAR_TICKS_TO_METERS_PER_SECOND;
        }
        public static double convertRPMToMeterPerSecond (double rpm){
            return Conversions.convertRPMToMeterPerSecond(rpm, MK4iSwerveConstants.WHEEL_CIRCUMFERENCE / (2 * Math.PI));
        }
    }






}
