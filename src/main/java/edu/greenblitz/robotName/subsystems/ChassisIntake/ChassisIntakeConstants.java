package edu.greenblitz.robotName.subsystems.ChassisIntake;

import edu.greenblitz.robotName.utils.PIDObject;
import edu.wpi.first.math.controller.PIDController;

public class ChassisIntakeConstants {
	public static final PIDController MOTOR_PID_P = new PIDController(0,0,0);
	public static final int INTAKE_ID;
	public static class SimulationConstants {
		
		public static final int NUMBER_OF_MOTORS;
		public static final double GEAR_RATIO;
		public static final double MOMENT_OF_INERTIA;
		
	}
	

}
