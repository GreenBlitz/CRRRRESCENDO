package edu.greenblitz.robotName.subsystems.shooter.funnel.neoFunnel;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class NeoFunnelConstants {
	
	public static final int MOTOR_ID = 7;
	
	public static double DEBOUNCE_TIME_FOR_LIMIT_SWITCH = 0.1;
	
	public static int BEAM_BREAKER_CHANNEL = 2;
	
	public static int CURRENT_LIMIT = 40;
	
	public static final GBSparkMax.SparkMaxConfObject FUNNEL_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
			.withIdleMode(CANSparkMax.IdleMode.kBrake)
			.withRampRate(RobotConstants.General.RAMP_RATE_VALUE)
			.withCurrentLimit(CURRENT_LIMIT);
}