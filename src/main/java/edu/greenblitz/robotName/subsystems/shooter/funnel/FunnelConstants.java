package edu.greenblitz.robotName.subsystems.shooter.funnel;


import edu.wpi.first.math.geometry.Rotation2d;

public class FunnelConstants {
	
	public static final double ROLL_POWER = 0.5;
	
	public static final double EJECT_POWER = -0.5;

	public static final double FUNNEL_TO_INTAKE_SPEED_CONVERSION = 5 / 4.0;

	public static final double FEEDER_COLLECT_SPEED = 100;

	public static final double INTAKE_VELOCITY = 175;
	
	public static final Rotation2d TOLERANCE = Rotation2d.fromRotations(0.5);

}