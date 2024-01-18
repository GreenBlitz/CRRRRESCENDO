package edu.greenblitz.robotName.subsystems.shooter.neoFlyWheel;

import edu.greenblitz.robotName.subsystems.shooter.ShooterConstants;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.interpolation.InterpolatingTreeMap;
import edu.wpi.first.math.interpolation.Interpolator;
import edu.wpi.first.math.interpolation.InverseInterpolator;

public class NeoFlyWheelConstants extends ShooterConstants {
    public static final int MOTOR_ID = 1;
    public static final SimpleMotorFeedforward FEEDFORWARD = new SimpleMotorFeedforward(1, 0, 0);
    public static final int PID_SLOT = 0;
    public static final InterpolatingTreeMap<Double, Double> VELOCITY_BY_DISTANCE_MAP = new InterpolatingTreeMap<>(
            InverseInterpolator.forDouble(), Interpolator.forDouble()
    );
}
