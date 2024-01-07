package edu.greenblitz.robotName.subsystems.swerve;

import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
public class SwerveChassisUtils {



    public static double joystickValueToLinearVelocity(double joystickValue, double velocityFactor) {
        return joystickValue * velocityFactor * (ChassisConstants.LINEAR_JOYSTICK_INVERTED ? -1 : 1);
    }

    public static double joystickValueToAngularVelocity(double joystickValue, double velocityFactor) {
        double factoredOutputValue = joystickValue * velocityFactor;
        return Math.min(factoredOutputValue, velocityFactor) * (ChassisConstants.ANGULAR_JOYSTICK_INVERTED ? -1 : 1);
    }
}
