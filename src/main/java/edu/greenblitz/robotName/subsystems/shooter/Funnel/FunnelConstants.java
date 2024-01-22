package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxLimitSwitch;
import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;


public class FunnelConstants {
	
	public static class SparkMaxConfigs{
		public static final int FUNNEL_ID = 0;
		public static final SparkMaxLimitSwitch.Type SWITCH_TYPE = SparkMaxLimitSwitch.Type.kNormallyClosed;
		public static double DEBOUNCE_TIME_FOR_LIMIT_SWITCH = 0.5;
		
		public static int CURRENT_LIMIT = 1;
		
		public static final GBSparkMax.SparkMaxConfObject FUNNEL_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
				.withIdleMode(CANSparkMax.IdleMode.kBrake)
				.withRampRate(RobotConstants.General.RAMP_RATE_VAL)
				.withCurrentLimit(CURRENT_LIMIT);
	}
	
	public static class SimulationConstants {
		public static final int NUMBER_OF_MOTORS = 0;
		public static final double GEAR_RATIO = 0;
		public static final double MOMENT_OF_INERTIA = 0;
		
	}
	
	
}
