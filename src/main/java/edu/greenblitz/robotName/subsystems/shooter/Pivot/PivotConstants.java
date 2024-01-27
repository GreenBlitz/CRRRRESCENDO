package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;

public class PivotConstants {

    public static final double LENGTH_OF_SHOOTER = 0.41;

    public static final double WIDTH_OF_SHOOTER = 0.22;

    public static final double SHOOTER_MASS_KG = 10;

    public static final double BACKWARD_ANGLE_LIMIT = Units.degreesToRadians(280);

    public static final double FORWARD_ANGLE_LIMIT = Units.degreesToRadians(340);

    public static final double RELATIVE_POSITION_CONVERSION_FACTOR = 0.0328;

    public static final double RELATIVE_VELOCITY_CONVERSION_FACTOR = 0.0030332432;

    public static final double ABSOLUTE_POSITION_CONVERSION_FACTOR = 0.00302;

    public static final double STARTING_ANGLE = Units.degreesToRadians(180);

    public static final Translation3d ROBOT_RELATIVE_PIVOT_POSITION = new Translation3d(0,0.1,0.15);

    public static final double kS = 1;

    public static final double kV = 1;

    public static final double kA = 1;

    public static final double TOLERANCE = Units.degreesToRadians(2);

}
