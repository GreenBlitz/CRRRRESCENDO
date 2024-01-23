package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.NeoFlyWheel;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.interpolation.InterpolatingTreeMap;
import edu.wpi.first.math.interpolation.Interpolator;
import edu.wpi.first.math.interpolation.InverseInterpolator;

public class NeoFlyWheelConstants {
	public static final int MOTOR_ID = 7;
	public static final SimpleMotorFeedforward FEEDFORWARD = new SimpleMotorFeedforward(1, 0, 0);
	public static final int PID_SLOT = 0;

}
