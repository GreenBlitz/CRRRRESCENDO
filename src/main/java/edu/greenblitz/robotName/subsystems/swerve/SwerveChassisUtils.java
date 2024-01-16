package edu.greenblitz.robotName.subsystems.swerve;

import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
public class SwerveChassisUtils {



    public static double joystickValueToOutputValue(double joystickValue, double velocityFactor, boolean isInverted) {
        double factoredOutputValue = joystickValue * velocityFactor;
        return Math.min(factoredOutputValue, velocityFactor) * (isInverted ? -1 : 1);    }

}
