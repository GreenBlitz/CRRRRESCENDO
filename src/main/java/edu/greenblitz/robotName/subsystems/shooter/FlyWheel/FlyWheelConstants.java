package edu.greenblitz.robotName.subsystems.shooter.FlyWheel;

import edu.wpi.first.math.interpolation.InterpolatingTreeMap;
import edu.wpi.first.math.interpolation.Interpolator;
import edu.wpi.first.math.interpolation.InverseInterpolator;

public class FlyWheelConstants {
	
	public static final double EPSILON_RPM = 50;
	
	public static final double MINIMUM_SHOOTING_SPEED_TIME_SECONDS = 2;
	
	public static final double SHOOTING_POWER = 0.9;
	
	public static final double SHOOTING_VELOCITY = 3000;
	
	public static final double SLOW_SHOOTING_VELOCITY = 90;
	
	public static final double SLOW_SHOOTING_DISTANCE_METERS = 0.5;
	
	public static final double FAST_SHOOTING_VELOCITY = 90;
	
	public static final double FAST_SHOOTING_DISTANCE_METERS = 3;

	public static final double SIMULATION_SHOOTING_SPEED_METERS_PER_SECOND = 2;

	public static final double SIMULATION_SHOOTING_TIME_SECOND = 10;

	public static final InterpolatingTreeMap<Double, Double> SHOOTING_VELOCITY_INTERPOLATOR = new InterpolatingTreeMap<>(
			InverseInterpolator.forDouble(),
			Interpolator.forDouble()
	);
	
	static {
		SHOOTING_VELOCITY_INTERPOLATOR.put(SLOW_SHOOTING_DISTANCE_METERS, SLOW_SHOOTING_VELOCITY);
		SHOOTING_VELOCITY_INTERPOLATOR.put(FAST_SHOOTING_DISTANCE_METERS, FAST_SHOOTING_VELOCITY);
	}
	
	public static final double LEFT_SHOOTING_POWER_CONVERSION_FACTOR = 0.8;
	
	public static final double DELAY_SECONDS_UNTIL_EXIT = 1;
}