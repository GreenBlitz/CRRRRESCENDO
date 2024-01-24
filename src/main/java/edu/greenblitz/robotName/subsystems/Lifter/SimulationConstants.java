package edu.greenblitz.robotName.subsystems.Lifter;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class SimulationConstants {
    public static final int NUMBER_OF_MOTORS = 0;
    public static final double GEAR_RATIO = 0;
    public static final double MOMENT_OF_INERTIA = 0;
    public static final double PID_KP = 3.6;
    public static final double PID_KI = 1;
    public static final double PID_KD = 0;
    public static final double MAX_VELOCITY = 5;
    public static final double MAX_ACCELERATION = 10;

    public static final ProfiledPIDController PID = new ProfiledPIDController(SimulationConstants.PID_KP, SimulationConstants.PID_KI, SimulationConstants.PID_KD, new TrapezoidProfile.Constraints(SimulationConstants.MAX_VELOCITY, SimulationConstants.MAX_ACCELERATION));
}