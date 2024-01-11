package edu.greenblitz.robotName.subsystems.swerve;

import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
public class SwerveChassisUtils {



    public static double joystickValueToLinearVelocity(double joystickValue, double velocityFactor) {
        double factoredOutputValue;
        if (Math.abs(joystickValue * velocityFactor) < velocityFactor) {
            factoredOutputValue = joystickValue * velocityFactor;
        } else {
            factoredOutputValue = velocityFactor;
        }
        return factoredOutputValue * (ChassisConstants.LINEAR_JOYSTICK_INVERTED ? -1 : 1);
    }

    public static double joystickValueToAngularVelocity(double joystickValue, double velocityFactor) {
        double factoredOutputValue;
        if (Math.abs(joystickValue * velocityFactor) < velocityFactor) {
            factoredOutputValue = joystickValue * velocityFactor;
        } else {
            factoredOutputValue = velocityFactor;
        }
        return factoredOutputValue * (ChassisConstants.ANGULAR_JOYSTICK_INVERTED ? -1 : 1);
    }
}
