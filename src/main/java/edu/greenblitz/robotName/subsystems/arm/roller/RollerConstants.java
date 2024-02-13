package edu.greenblitz.robotName.subsystems.arm.roller;

import edu.wpi.first.math.geometry.Rotation2d;

public class RollerConstants {

    public static final double ROLL_FORWARD_POWER = 0.3;

    public static final double ROLL_BACKWARD_POWER = -1;

    public static final double SAFETY_TIME_AFTER_NOTE_IS_RELEASED = 0.5;

    public static final Rotation2d SAFETY_ROTATIONS_TILL_OBJECT_ENTERED = Rotation2d.fromRotations(2);

    public static final Rotation2d TOLERANCE = Rotation2d.fromRotations(0.5);
}