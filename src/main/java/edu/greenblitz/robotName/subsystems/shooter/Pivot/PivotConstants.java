package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import edu.wpi.first.math.util.Units;

public class PivotConstants {

    public static final double LENGTH_OF_SHOOTER = 0.418;

    public static final double SHOOTER_MASS_KG = 10;

    public static final double BACKWARD_ANGLE_LIMIT = Units.degreesToRadians(110);

    public static final double FORWARD_ANGLE_LIMIT = Units.degreesToRadians(170);

    public static final double TRANSFER_ANGLE = Units.degreesToRadians(120);

    public static final double RELATIVE_POSITION_CONVERSION_FACTOR = 0.0328;

    public static final double RELATIVE_VELOCITY_CONVERSION_FACTOR = 0.0030332432;

    public static final double ABSOLUTE_POSITION_CONVERSION_FACTOR = 0.00302;

    public static final double STARTING_ANGLE = Units.degreesToRadians(140);

    public static final double PICK_UP_ANGLE = Units.degreesToRadians(150);

    public static final double kS = 1;

    public static final double kV = 1;

    public static final double kG = 1;

    public static final double kA = 1;

    public static final double TOLERANCE = Units.degreesToRadians(0.5);


}
