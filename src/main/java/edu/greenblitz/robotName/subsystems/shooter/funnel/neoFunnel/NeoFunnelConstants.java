package edu.greenblitz.robotName.subsystems.shooter.funnel.neoFunnel;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkLimitSwitch;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class NeoFunnelConstants {
	
	public static final int MOTOR_ID = 52;

	public static final boolean IS_INVERTED = true;
	
	public static final double kS = 0.27;
	
	public static final double kV = 0.01025;
	
	public static final double kA = 0.03;
	
	public static final double kP = 3;
	
	public static final int VELOCITY_PID_SLOT = 0;
	
	public static SimpleMotorFeedforward SIMPLE_MOTOR_FEED_FORWARD = new SimpleMotorFeedforward(kS, kV, kA);
	
	public static final PIDObject PID_CONTROLLER = new PIDObject(kP, 0, 0);
	
	public static final double DEBOUNCE_TIME_FOR_LIMIT_SWITCH = 0.1;
	
	public static final boolean IS_BEAM_BREAKER_ENABLE = false;

	public static final SparkLimitSwitch.Type BEAM_BREAKER_TYPE = SparkLimitSwitch.Type.kNormallyOpen;

	public static final int CURRENT_LIMIT = 40;
	
	public static final GBSparkMax.SparkMaxConfObject FUNNEL_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
			.withIdleMode(CANSparkMax.IdleMode.kBrake)
			.withRampRate(RobotConstants.General.RAMP_RATE_VALUE)
			.withCurrentLimit(CURRENT_LIMIT)
			.withInverted(IS_INVERTED)
			.withPID(PID_CONTROLLER)
			.withVelocityConversionFactor(1 / 5.0);
}