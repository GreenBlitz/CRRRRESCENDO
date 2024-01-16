package edu.greenblitz.robotName.subsystems.shooter.neoFlyWheel;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.interpolation.InterpolatingTreeMap;
import edu.wpi.first.math.interpolation.Interpolator;
import edu.wpi.first.math.interpolation.InverseInterpolator;

public class NeoFlyWheelConstants {
	public static final int MOTOR_ID = 1;
	public static final SimpleMotorFeedforward FEEDFORWARD = new SimpleMotorFeedforward(1,0,0);
	public static final InterpolatingTreeMap<Double,Double> VELOCITY_BY_DISTANCE_MAP = new InterpolatingTreeMap<>(
			InverseInterpolator.forDouble(), Interpolator.forDouble()
	);
	static {
		VELOCITY_BY_DISTANCE_MAP.put(1.0,1.0);
	}
}
