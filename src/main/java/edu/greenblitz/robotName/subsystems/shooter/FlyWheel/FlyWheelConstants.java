package edu.greenblitz.robotName.subsystems.shooter.FlyWheel;

import edu.wpi.first.math.interpolation.InterpolatingTreeMap;
import edu.wpi.first.math.interpolation.Interpolator;
import edu.wpi.first.math.interpolation.InverseInterpolator;

public class FlyWheelConstants {

    public static final double EPSILON_RPM = 10;

    public static final double SHOOTING_SPEED_TIME = 1;

    public static final double DIFFERENTIATING_RATIO = 0.8;

    public static final InterpolatingTreeMap<Double, Double> DISTANCE_TO_ANGLE_MAP = new InterpolatingTreeMap<>(
            InverseInterpolator.forDouble(), Interpolator.forDouble()
    );

    static { // First input is the distance, Second input is the angle
        DISTANCE_TO_ANGLE_MAP.put(0.0,0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0,0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0,0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0,0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0,0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0,0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0,0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0,0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0,0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0,0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0,0.0);
    }

}
