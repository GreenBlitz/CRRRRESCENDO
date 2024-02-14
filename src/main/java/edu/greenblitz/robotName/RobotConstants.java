package edu.greenblitz.robotName;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.PowerDistribution;

public class RobotConstants {
	
	public static final Robot.RobotType ROBOT_TYPE = Robot.RobotType.ROBOT_NAME;
	
	public static final String SIMULATION_LOG_PATH = System.getProperty("user.home") + "\\Desktop\\SimulationLogs";
	
	public static final String USB_LOG_PATH = "/usb/";
	
	public static final String SAFE_ROBORIO_LOG_PATH = "/logs/";
	
	public static class General {
		
		public static final int POWER_DISTRIBUTION_CAN_ID = 20;
		
		public static final PowerDistribution.ModuleType POWER_DISTRIBUTION_TYPE = PowerDistribution.ModuleType.kRev;
		
		public final static double RAMP_RATE_VALUE = 0.4;
		
		public static final double SAFETY_POWER_CONVERSION_FACTOR = 1;
		
		public static class Motors {
			
			public final static double SPARKMAX_TICKS_PER_RADIAN = Math.PI * 2;
			
			public final static double SPARKMAX_VELOCITY_UNITS_PER_RPM = 1;
			
			public final static double NEO_TICKS_PER_ROTATION = 42;
			
			public static final double NEO_PHYSICAL_TICKS_TO_RADIANS = SPARKMAX_TICKS_PER_RADIAN / NEO_TICKS_PER_ROTATION; //do not use unless you understand the meaning
			
			public final static double FALCON_REVOLUTIONS_PER_RADIAN = 2 * Math.PI;
			
			public final static double SIGNAL_FREQUENCY_HERTZ = 100;
			
			public final static double IS_SWITCH_CLOSED = 0;
		}
	}
	
	public static class Frankenstein {
		
		public static final double ROBOT_LENGTH_IN_METERS = 0.89;
		
		public static final double BUMPER_LENGTH = 0.05;
	}
	
	public static class SimulationConstants {
		
		public static final double TIME_STEP = 0.02;
		
		public static final double BATTERY_VOLTAGE = 12;
		
		public static final double MAX_MOTOR_VOLTAGE = 12;
		
		public static final double MIN_MOTOR_VOLTAGE = -12;
		
		public static final Translation3d ARM_TO_ROBOT = new Translation3d(0, 0, 1.2);
	}
	
	public static class Joystick {
		
		public static final int MAIN = 0;
		
		public static final int SECOND = 1;
		
		public static final int THIRD = 2;
		
		public static final int FOURTH = 3;
	}
	
	public static class Pneumatics {
		
		public static final int PCM_ID = 20;
	}
}