package edu.greenblitz.robotName.subsystems.Lifter.SimulationLifter;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class SimulationLifterConstants {

    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 30;

    public static final double DEBOUNCE_TIME_FOR_SWITCH = 0.1;

    public static final ProfiledPIDController SIMULATION_PID = new ProfiledPIDController(3, 0, 0, new TrapezoidProfile.Constraints(5, 10));

    public static final int NUMBER_OF_SOLENOID_MOTORS = 1;

    public static final double MOTOR_GEARING = 1;

    public static final double MOTOR_JKG_METERS_SQUARED = 0.005;
}