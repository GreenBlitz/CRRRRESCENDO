package edu.greenblitz.robotName.subsystems.arm.elbow;

import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation3d;

public class ElbowConstants {

    public enum PresetPositions {
        SAFE(Rotation2d.fromDegrees(-65)),
        SCORE(Rotation2d.fromDegrees(85)),
        STARTING(Rotation2d.fromDegrees(0)),
        TRANSFER(Rotation2d.fromDegrees(-75));

        public final Rotation2d ANGLE;
        PresetPositions(Rotation2d angle){
            this.ANGLE = angle;
        }
    }

    public static final double ARM_LENGTH = 0.44;

    public static final double ARM_MASS_KG = 0.44;

    public static final Rotation2d BACKWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(-75);

    public static final Rotation2d FORWARD_ANGLE_LIMIT = Rotation2d.fromDegrees(90);

    public static final Pair<Rotation2d,Rotation2d> SHOOTER_COLLISION_RANGE = new Pair<>(BACKWARD_ANGLE_LIMIT,PresetPositions.SAFE.ANGLE);

    public static final Translation3d ELBOW_POSITION_RELATIVE_TO_ROBOT = new Translation3d(-0.1,0,0.6);

    public static final double GEAR_RATIO = 1;

    public static final Rotation2d TOLERANCE = Rotation2d.fromDegrees(0.5);

    public static final int CURRENT_LIMIT = 40;



}
