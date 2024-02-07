package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;

public class PivotConstants {

    public static final double SHOOTER_MASS_KG = 10;

    public static final double LENGTH_OF_SHOOTER = 0.418;

    public enum PresetPositions {

        STARTING(Rotation2d.fromDegrees(45)),
        TRANSFER(Rotation2d.fromDegrees(10)),
        SAFE(Rotation2d.fromDegrees(55)),
        PICK_UP(Rotation2d.fromDegrees(45));

        public final Rotation2d ANGLE;

        PresetPositions(Rotation2d angle){
            this.ANGLE = angle;
        }

    }

    public static final Rotation2d BACKWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(0);

    public static final Rotation2d FORWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(62);

    public static final double RELATIVE_POSITION_CONVERSION_FACTOR = 0.0328;

    public static final double RELATIVE_VELOCITY_CONVERSION_FACTOR = 0.0030332432;

    public static final Translation3d ROBOT_RELATIVE_PIVOT_POSITION = new Translation3d(0,0.1,0.15);

    public static final Rotation2d TOLERANCE = Rotation2d.fromDegrees(0.5);

}
