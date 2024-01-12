package edu.greenblitz.robotName.subsystems.swerve.Modules.mk4iSwerveModule;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.SwerveModuleConfigObject;
import edu.wpi.first.math.geometry.Rotation2d;

public class MK4iSwerveConstants {
	
	public static SwerveModuleConfigObject MK4I_MODULE_FRONT_LEFT = new SwerveModuleConfigObject(1, 0, 1, Rotation2d.fromRotations(0.8486328125),false);
	public static SwerveModuleConfigObject MK4I_MODULE_FRONT_RIGHT = new SwerveModuleConfigObject(3, 2, 2,Rotation2d.fromRotations(0.2939453125) ,true);
	public static SwerveModuleConfigObject MK4I_MODULE_BACK_LEFT = new SwerveModuleConfigObject(5, 4, 3, Rotation2d.fromRotations(0.5524),false);
	public static SwerveModuleConfigObject MK4I_MODULE_BAK_RIGHT = new SwerveModuleConfigObject(7, 6, 4, Rotation2d.fromRotations(0.8718),true);
	
	public static final double ANGULAR_GEAR_RATIO = (150.0 / 7);
	public static final double LINEAR_GEAR_RATIO = 8.14;
	
	public static final double ks = 0.16411;
	public static final double kv = 2.6824;
	public static final double ka = 0.25968;
	
	public static final double WHEEL_CIRCUMFERENCE = 0.0517 * 2 * Math.PI;
	public static final double LINEAR_TICKS_TO_METERS = RobotConstants.General.Motors.FALCON_TICKS_PER_RADIAN * WHEEL_CIRCUMFERENCE / 2 / Math.PI / LINEAR_GEAR_RATIO;
	public static final double ANGLE_TICKS_TO_WHEEL_TO_RPM = RobotConstants.General.Motors.FALCON_VELOCITY_UNITS_PER_RPM / ANGULAR_GEAR_RATIO;
	public static final double LINEAR_TICKS_TO_METERS_PER_SECOND = RobotConstants.General.Motors.FALCON_VELOCITY_UNITS_PER_RPM / LINEAR_GEAR_RATIO * WHEEL_CIRCUMFERENCE / 60;
	public static final double ANGLE_TICKS_TO_RADIANS = RobotConstants.General.Motors.FALCON_TICKS_PER_RADIAN / ANGULAR_GEAR_RATIO;
	public static final double MAG_ENCODER_TICKS_TO_FALCON_TICKS = 2 * Math.PI / ANGLE_TICKS_TO_RADIANS;

	public static final TalonFXConfiguration ANGULAR_FALCON_CONFIG_OBJECT = new TalonFXConfiguration();
	static {
		Slot0Configs PID_CONFIGS = new Slot0Configs();
		PID_CONFIGS.kP = 0.05;
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

        var MOTOR_OUTPUT_CONFIGS = new MotorOutputConfigs();
		MOTOR_OUTPUT_CONFIGS.NeutralMode = NeutralModeValue.Brake;
		MOTOR_OUTPUT_CONFIGS.Inverted = InvertedValue.CounterClockwise_Positive; //true

		MotionMagicConfigs MOTION_MAGIC_CONFIGS = new MotionMagicConfigs();
		MOTION_MAGIC_CONFIGS.MotionMagicAcceleration = 2;
		MOTION_MAGIC_CONFIGS.MotionMagicCruiseVelocity = 2;
		MOTION_MAGIC_CONFIGS.MotionMagicJerk = 2;

		ANGULAR_FALCON_CONFIG_OBJECT.Slot0 = PID_CONFIGS;
		ANGULAR_FALCON_CONFIG_OBJECT.CurrentLimits = CURRENT_LIMITS_CONFIGS;
		ANGULAR_FALCON_CONFIG_OBJECT.ClosedLoopRamps = CLOSED_LOOP_RAMPS_CONFIGS;
		ANGULAR_FALCON_CONFIG_OBJECT.MotorOutput = MOTOR_OUTPUT_CONFIGS;
		ANGULAR_FALCON_CONFIG_OBJECT.MotionMagic = MOTION_MAGIC_CONFIGS;

	}
	public static final TalonFXConfiguration LINEAR_FALCON_CONFIG_OBJECT = new TalonFXConfiguration();
	static {
		Slot0Configs PID_CONFIGS = new Slot0Configs();
		PID_CONFIGS.kP = 15;
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

		LINEAR_FALCON_CONFIG_OBJECT.Slot0 = PID_CONFIGS;
		LINEAR_FALCON_CONFIG_OBJECT.CurrentLimits = CURRENT_LIMITS_CONFIGS;
		LINEAR_FALCON_CONFIG_OBJECT.ClosedLoopRamps = CLOSED_LOOP_RAMPS_CONFIGS;
		LINEAR_FALCON_CONFIG_OBJECT.TorqueCurrent = TORQUE_CURRENT_CONFIGS;
		LINEAR_FALCON_CONFIG_OBJECT.MotorOutput = MOTOR_OUTPUT_CONFIGS;



	}
	public static final double LINEAR_MOTOR_FREE_CURRENT = 1.5;
}
