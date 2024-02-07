package edu.greenblitz.robotName.subsystems.shooter.Funnel;


import edu.wpi.first.math.geometry.Rotation2d;

public class FunnelConstants {

	public static final double ROLL_POWER = 1;

	public static final double EJECT_POWER = -1;

	public static final Rotation2d SAFETY_ROTATIONS_TILL_OBJECT_EXITED = Rotation2d.fromRadians(2);

	public static final Rotation2d TOLERANCE = Rotation2d.fromRotations(0.5);
}
