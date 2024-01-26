package edu.greenblitz.robotName.subsystems.arm.ElbowUtils;

import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class ElbowConstants {

    public enum ImportantPlaces{

        STARTING(Rotation2d.fromDegrees(100)),
        TRANSFER(Rotation2d.fromDegrees(10));

        public Rotation2d angle;
        ImportantPlaces(Rotation2d angle){
            this.angle = angle;
        }

    }

    public static final double ARM_LENGTH = 0.53;

    public static final double ARM_MASS_KG = 1;

    public static final Rotation2d BACKWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(-160);

    public static final Rotation2d FORWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(30);

    public static final double RELATIVE_POSITION_CONVERSION_FACTOR = 0.0328;

    public static final double RELATIVE_VELOCITY_CONVERSION_FACTOR = 1;

    public static final double ABSOLUTE_POSITION_CONVERSION_FACTOR = 1;

    public static final double kS = 1;

    public static final double kV = 1;

    public static final double kG = 1;

    public static final double kA = 1;

    public static final double TOLERANCE = Units.degreesToRadians(0.5);


}
