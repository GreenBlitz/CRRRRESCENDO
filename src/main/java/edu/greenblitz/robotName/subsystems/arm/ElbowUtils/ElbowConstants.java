package edu.greenblitz.robotName.subsystems.arm.ElbowUtils;

import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;

public class ElbowConstants {

    public enum PresetPositions {
        SAFE(Rotation2d.fromDegrees(30)),
        STARTING(Rotation2d.fromDegrees(180)),
        TRANSFER(Rotation2d.fromDegrees(10));

        public final Rotation2d ANGLE;
        PresetPositions(Rotation2d angle){
            this.ANGLE = angle;
        }
    }

    public static final double ARM_LENGTH = 0.44;

    public static final double ARM_MASS_KG = 1;

    public static final Rotation2d BACKWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(5);

    public static final Rotation2d FORWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(180);

    public static final Pair<Rotation2d,Rotation2d> SHOOTER_COLLISION_RANGE = new Pair<>(BACKWARD_ANGLE_LIMIT,Rotation2d.fromDegrees(20));

    public static final Translation3d ELBOW_POSITION_RELATIVE_TO_ROBOT = new Translation3d(0,0.1,0.6);

    public static final double RELATIVE_POSITION_CONVERSION_FACTOR = 0.0328;

    public static final double RELATIVE_VELOCITY_CONVERSION_FACTOR = 1;

    public static final double ABSOLUTE_POSITION_CONVERSION_FACTOR = 1;

    public static final double ABSOLUTE_VELOCITY_CONVERSION_FACTOR = 0.00302;

    public static final double STARTING_ANGLE = 1;

    public static final double TOLERANCE = Units.degreesToRadians(0.5);

    public static final int CURRENT_LIMIT = 40;



}
