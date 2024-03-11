package edu.greenblitz.robotName.subsystems.climber.lifter.simulationLifter;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class SimulationLifterConstants {

    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 174;

    public static final ProfiledPIDController SIMULATION_PID = new ProfiledPIDController(3, 0, 0, new TrapezoidProfile.Constraints(5, 10));

}