package edu.greenblitz.robotName.subsystems.arm.roller;

import edu.wpi.first.math.geometry.Rotation2d;

public class RollerConstants {

    public static final double ROLL_FORWARD_POWER = 1;

    public static final double ROLL_BACKWARD_POWER = -0.8;
    
    public static final double HAND_CONTROL_POWER = 0.40;

    public static final Rotation2d SAFETY_ROTATIONS_TILL_OBJECT_ENTERED = Rotation2d.fromRotations(-1);

    public static final Rotation2d SAFETY_ROTATIONS_TILL_OBJECT_OUT = Rotation2d.fromRotations(8);

    public static final Rotation2d TOLERANCE = Rotation2d.fromRotations(0.04);
}