package edu.greenblitz.robotName.subsystems.Lifter;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;

public class LifterConstants {

    public static final Translation3d ROBOT_RELATIVE_LIFTER_POSITION = new Translation3d(0,0.24,0.35);


    public static final Rotation3d ROBOT_RELATIVE_LIFTER_ROTATION = new Rotation3d(Math.PI,0,-Math.PI/2);

    public static final double ENCODER_POSE_WHEN_RESET = 0;

    public static final double TOLERANCE = 0.04;

    public static final Rotation2d LIFTER_RETRACTED_POSITION = Rotation2d.fromDegrees(0);
    public static final Rotation2d LIFTER_EXTENDED_POSITION = Rotation2d.fromDegrees(135);
}
