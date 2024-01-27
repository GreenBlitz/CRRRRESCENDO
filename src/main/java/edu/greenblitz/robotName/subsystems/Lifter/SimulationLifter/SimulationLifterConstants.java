package edu.greenblitz.robotName.subsystems.Lifter.SimulationLifter;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class SimulationLifterConstants {

    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 30;

    public static final double MOMENT_OF_INERTIA = 0.03;
    public static final double DEBOUNCE_TIME_FOR_SWITCH = 0.1;

    public static final ProfiledPIDController SIMULATION_PID = new ProfiledPIDController(3, 0, 0, new TrapezoidProfile.Constraints(5, 10));
}