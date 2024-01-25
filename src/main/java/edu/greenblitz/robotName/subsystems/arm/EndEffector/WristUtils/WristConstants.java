package edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils;

import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class WristConstants {

    public static final double LENGTH_OF_ENDEFFECTOR = 0.3;

    public static final double SHOOTER_MASS_KG = 10;

    public static final Rotation2d BACKWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(20);

    public static final Rotation2d FORWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(315);

    public static final Rotation2d TRANSFER_ANGLE = Rotation2d.fromDegrees(315);

    public static final Pair<Rotation2d,Rotation2d> IN_ROBOT_DESTRUCTION_RANGE = new Pair<>(Rotation2d.fromDegrees(100),Rotation2d.fromDegrees(200));

    public static final double RELATIVE_POSITION_CONVERSION_FACTOR = 0.0328;

    public static final double RELATIVE_VELOCITY_CONVERSION_FACTOR = 0.0030332432;

    public static final double ABSOLUTE_POSITION_CONVERSION_FACTOR = 0.00302;
  
    public static final double ABSOLUTE_VELOCITY_CONVERSION_FACTOR = 0.00302;

    public static final double STARTING_ANGLE = 1;

    public static final double kS = 1;

    public static final double kV = 1;

    public static final double kG = 1;

    public static final double kA = 1;

    public static final double TOLERANCE = Units.degreesToRadians(2);

    public static final int CURRENT_LIMIT = 40;

}
