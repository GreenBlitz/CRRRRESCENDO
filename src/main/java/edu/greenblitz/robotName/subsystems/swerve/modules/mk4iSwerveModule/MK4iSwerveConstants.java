package edu.greenblitz.robotName.subsystems.swerve.modules.mk4iSwerveModule;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.SwerveModuleConfigObject;
import edu.wpi.first.math.util.Units;

import static edu.greenblitz.robotName.RobotConstants.General.CANIVORE_NAME;

public class MK4iSwerveConstants {
	
	public static SwerveModuleConfigObject MK4I_MODULE_FRONT_LEFT = new SwerveModuleConfigObject(CANIVORE_NAME, 5, 4, 0, false, true);
	
	public static SwerveModuleConfigObject MK4I_MODULE_FRONT_RIGHT = new SwerveModuleConfigObject(CANIVORE_NAME, 3, 2, 1, true, true);
	
	public static SwerveModuleConfigObject MK4I_MODULE_BACK_LEFT = new SwerveModuleConfigObject(CANIVORE_NAME, 1, 43, 2, false, false);
	
	public static SwerveModuleConfigObject MK4I_MODULE_BACK_RIGHT = new SwerveModuleConfigObject(CANIVORE_NAME, 7, 6, 3, false, true);
	
	public static final double ANGULAR_GEAR_RATIO = (150.0 / 7);
	
	public static final double LINEAR_GEAR_RATIO = 8.14;
	
	public static final double ks = 0.16411;
	
	public static final double kv = 2.6824;
	
	public static final double ka = 0.25968;
	
	public static final double WHEEL_RADIUS = Units.inchesToMeters(2);
	
	public static final double WHEEL_CIRCUMFERENCE = WHEEL_RADIUS * 2 * Math.PI;
	
	public static final double LINEAR_REVOLUTIONS_TO_METERS = RobotConstants.General.Motors.FALCON_REVOLUTIONS_PER_RADIAN * WHEEL_CIRCUMFERENCE / 2 / Math.PI / LINEAR_GEAR_RATIO;
	
	public static final double ANGLE_REVOLUTIONS_TO_RADIANS = RobotConstants.General.Motors.FALCON_REVOLUTIONS_PER_RADIAN / ANGULAR_GEAR_RATIO;
	
	public static final TalonFXConfiguration ANGULAR_FALCON_CONFIG_OBJECT = new TalonFXConfiguration();
	
	static {
		FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();
		FEEDBACK_CONFIGS.SensorToMechanismRatio = ANGULAR_GEAR_RATIO;

		Slot0Configs PID_CONFIGS = new Slot0Configs();
		PID_CONFIGS.kP = 45;
		PID_CONFIGS.kI = 0;
		PID_CONFIGS.kD = 1;
		PID_CONFIGS.kS = 0.31;
		PID_CONFIGS.kV = 0;
		
		CurrentLimitsConfigs CURRENT_LIMITS_CONFIGS = new CurrentLimitsConfigs();
		CURRENT_LIMITS_CONFIGS.StatorCurrentLimitEnable = true;
		CURRENT_LIMITS_CONFIGS.StatorCurrentLimit = 40;
		CURRENT_LIMITS_CONFIGS.SupplyCurrentThreshold = 2;
		
		ClosedLoopRampsConfigs CLOSED_LOOP_RAMPS_CONFIGS = new ClosedLoopRampsConfigs();
		CLOSED_LOOP_RAMPS_CONFIGS.VoltageClosedLoopRampPeriod = 0.1;
		
		MotorOutputConfigs MOTOR_OUTPUT_CONFIGS = new MotorOutputConfigs();
		MOTOR_OUTPUT_CONFIGS.NeutralMode = NeutralModeValue.Brake;
		MOTOR_OUTPUT_CONFIGS.Inverted = InvertedValue.CounterClockwise_Positive;

		ANGULAR_FALCON_CONFIG_OBJECT.Slot0 = PID_CONFIGS;
		ANGULAR_FALCON_CONFIG_OBJECT.CurrentLimits = CURRENT_LIMITS_CONFIGS;
		ANGULAR_FALCON_CONFIG_OBJECT.ClosedLoopRamps = CLOSED_LOOP_RAMPS_CONFIGS;
		ANGULAR_FALCON_CONFIG_OBJECT.MotorOutput = MOTOR_OUTPUT_CONFIGS;
		ANGULAR_FALCON_CONFIG_OBJECT.Feedback = FEEDBACK_CONFIGS;
	}
	
	public static final TalonFXConfiguration LINEAR_FALCON_CONFIG_OBJECT = new TalonFXConfiguration();
	
	static {
		Slot0Configs PID_CONFIGS = new Slot0Configs();
		PID_CONFIGS.kP = 5;
		PID_CONFIGS.kI = 0;
		PID_CONFIGS.kD = 0;
		PID_CONFIGS.kS = 0;
		PID_CONFIGS.kV = 0;
		
		CurrentLimitsConfigs CURRENT_LIMITS_CONFIGS = new CurrentLimitsConfigs();
		CURRENT_LIMITS_CONFIGS.StatorCurrentLimitEnable = true;
		CURRENT_LIMITS_CONFIGS.StatorCurrentLimit = 40;
		CURRENT_LIMITS_CONFIGS.SupplyCurrentThreshold = 2;
		
		ClosedLoopRampsConfigs CLOSED_LOOP_RAMPS_CONFIGS = new ClosedLoopRampsConfigs();
		CLOSED_LOOP_RAMPS_CONFIGS.VoltageClosedLoopRampPeriod = 0.1;
		
		TorqueCurrentConfigs TORQUE_CURRENT_CONFIGS = new TorqueCurrentConfigs();
		TORQUE_CURRENT_CONFIGS.PeakForwardTorqueCurrent = 60;
		TORQUE_CURRENT_CONFIGS.PeakReverseTorqueCurrent = -60;
		
		MotorOutputConfigs MOTOR_OUTPUT_CONFIGS = new MotorOutputConfigs();
		MOTOR_OUTPUT_CONFIGS.NeutralMode = NeutralModeValue.Brake;
		
		FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();
		FEEDBACK_CONFIGS.SensorToMechanismRatio = LINEAR_GEAR_RATIO;
		
		LINEAR_FALCON_CONFIG_OBJECT.Slot0 = PID_CONFIGS;
		LINEAR_FALCON_CONFIG_OBJECT.CurrentLimits = CURRENT_LIMITS_CONFIGS;
		LINEAR_FALCON_CONFIG_OBJECT.ClosedLoopRamps = CLOSED_LOOP_RAMPS_CONFIGS;
		LINEAR_FALCON_CONFIG_OBJECT.TorqueCurrent = TORQUE_CURRENT_CONFIGS;
		LINEAR_FALCON_CONFIG_OBJECT.MotorOutput = MOTOR_OUTPUT_CONFIGS;
		LINEAR_FALCON_CONFIG_OBJECT.Feedback = FEEDBACK_CONFIGS;
	}
	
	public static final double LINEAR_MOTOR_FREE_CURRENT = 1.5;
}
