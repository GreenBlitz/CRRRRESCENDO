package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import edu.wpi.first.math.interpolation.InterpolatingTreeMap;
import edu.wpi.first.math.interpolation.Interpolator;
import edu.wpi.first.math.interpolation.InverseInterpolator;
import edu.wpi.first.math.util.Units;

public class PivotConstants {

    public static final double LENGTH_OF_SHOOTER = 0.3;

    public static final double SHOOTER_MASS_KG = 10;

    public static final double BACKWARD_ANGLE_LIMIT = Units.degreesToRadians(20);

    public static final double FORWARD_ANGLE_LIMIT = Units.degreesToRadians(340);

    public static final double RELATIVE_POSITION_CONVERSION_FACTOR = 0.0328;

    public static final double RELATIVE_VELOCITY_CONVERSION_FACTOR = 0.0030332432;

    public static final double ABSOLUTE_POSITION_CONVERSION_FACTOR = 0.00302;

    public static final double STARTING_ANGLE = Units.degreesToRadians(180);

    public static final double kS = 1;

    public static final double kV = 1;

    public static final double kG = 1;

    public static final double kA = 1;

    public static final double TOLERANCE = Units.degreesToRadians(2);

    public static final InterpolatingTreeMap<Double, Double> DISTANCE_TO_ANGLE_MAP = new InterpolatingTreeMap<>(
            InverseInterpolator.forDouble(), Interpolator.forDouble()
    );

    static { // First input is the distance, Second input is the angle
        DISTANCE_TO_ANGLE_MAP.put(0.0, 0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0, 0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0, 0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0, 0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0, 0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0, 0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0, 0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0, 0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0, 0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0, 0.0);
        DISTANCE_TO_ANGLE_MAP.put(0.0, 0.0);
    }


}