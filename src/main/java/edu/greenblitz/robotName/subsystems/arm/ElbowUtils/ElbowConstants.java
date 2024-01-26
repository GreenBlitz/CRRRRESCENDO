package edu.greenblitz.robotName.subsystems.arm.ElbowUtils;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class ElbowConstants {

    public static final double ARM_LENGTH = 0.44;

    public static final double ARM_MASS_KG = 1;

    public static final Rotation2d BACKWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(20);

    public static final Rotation2d FORWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(320);

    public static final double RELATIVE_POSITION_CONVERSION_FACTOR = 0.0328;

    public static final double RELATIVE_VELOCITY_CONVERSION_FACTOR = 1;

    public static final double ABSOLUTE_POSITION_CONVERSION_FACTOR = 1;

    public static final double STARTING_ANGLE = 1;

    public static final double kS = 1;

    public static final double kV = 1;

    public static final double kG = 1;

    public static final double kA = 1;

    public static final double TOLERANCE = Units.degreesToRadians(2);


}
