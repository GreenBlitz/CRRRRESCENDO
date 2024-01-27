package edu.greenblitz.robotName.subsystems.arm.ElbowUtils;

import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class ElbowConstants {

    public enum ImportantPlaces{

        STARTING(Rotation2d.fromDegrees(250)),
        SAFE(Rotation2d.fromDegrees(325)),
        TRANSFER(Rotation2d.fromDegrees(370));

        public Rotation2d angle;
        ImportantPlaces(Rotation2d angle){
            this.angle = angle;
        }

    }

    public static final double ARM_LENGTH = 0.53;

    public static final double ARM_MASS_KG = 1;

    public static final Rotation2d BACKWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(200);

    public static final Rotation2d FORWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(400);

    public static final Pair<Rotation2d,Rotation2d> DANGEROUS_ZONE = new Pair<>(Rotation2d.fromDegrees(340),FORWARD_ANGLE_LIMIT);

    public static final double RELATIVE_POSITION_CONVERSION_FACTOR = 0.0328;

    public static final double RELATIVE_VELOCITY_CONVERSION_FACTOR = 1;

    public static final double ABSOLUTE_POSITION_CONVERSION_FACTOR = 1;

    public static final double kS = 1;

    public static final double kV = 1;

    public static final double kG = 1;

    public static final double kA = 1;

    public static final double TOLERANCE = Units.degreesToRadians(0.5);


}
