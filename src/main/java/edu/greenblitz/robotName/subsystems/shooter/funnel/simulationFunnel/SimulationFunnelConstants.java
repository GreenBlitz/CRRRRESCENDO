package edu.greenblitz.robotName.subsystems.shooter.funnel.simulationFunnel;

import edu.wpi.first.math.controller.PIDController;

public class SimulationFunnelConstants {
	
	public static final int NUMBER_OF_MOTORS = 1;
	
	public static final double GEAR_RATIO = 1;
	
	public static final double MOMENT_OF_INERTIA = 1;
	
	public static final PIDController SIMULATION_PID = new PIDController(1, 0, 0);
	
}