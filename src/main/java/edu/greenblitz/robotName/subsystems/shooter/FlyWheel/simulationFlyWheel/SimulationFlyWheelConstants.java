package edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.simulationFlyWheel;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.wpi.first.math.controller.PIDController;

public class SimulationFlyWheelConstants extends FlyWheelConstants {
    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEARING = 8;

    public static final double J_KG_METERS_SQUARED = 1;

    public static final double POWER_TO_VELOCITY_FACTOR = 0.1;

    public static final PIDController SIMULATION_PID = new PIDController(0.3,0,0.3);
}
