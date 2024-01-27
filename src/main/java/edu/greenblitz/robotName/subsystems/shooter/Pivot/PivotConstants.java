package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class PivotConstants {

    public enum ImportantPlaces{

        STARTING(Rotation2d.fromDegrees(140)),
        TRANSFER(Rotation2d.fromDegrees(100)),
        PICK_UP(Rotation2d.fromDegrees(150));

        public final Rotation2d ANGLE;

        ImportantPlaces(Rotation2d angle){
            this.ANGLE = angle;
        }

    }

    public static final double LENGTH_OF_SHOOTER = 0.418;

    public static final double SHOOTER_MASS_KG = 10;

    public static final double BACKWARD_ANGLE_LIMIT = Units.degreesToRadians(90);

    public static final Rotation2d FORWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(170);

    public static final double RELATIVE_POSITION_CONVERSION_FACTOR = 0.0328;

    public static final double RELATIVE_VELOCITY_CONVERSION_FACTOR = 0.0030332432;

    public static final double ABSOLUTE_POSITION_CONVERSION_FACTOR = 0.00302;

    public static final double kS = 1;

    public static final double kV = 1;

    public static final double kG = 1;

    public static final double kA = 1;

    public static final double TOLERANCE = Units.degreesToRadians(0.5);

    public static final double NO_COLLISION_DELAY_SECONDS = 0.7;


}
