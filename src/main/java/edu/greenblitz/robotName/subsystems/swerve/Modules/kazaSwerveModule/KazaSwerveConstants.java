package edu.greenblitz.robotName.subsystems.swerve.Modules.kazaSwerveModule;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.SwerveModuleConfigObject;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class KazaSwerveConstants {
	
	public static final SwerveModuleConfigObject KAZA_SWERVE_MODULE_FRONT_LEFT = new SwerveModuleConfigObject(1, 10, 0, false);
	public static final SwerveModuleConfigObject KAZA_SWERVE_MODULE_FRONT_RIGHT = new SwerveModuleConfigObject(3, 11, 2, true);
	public static final SwerveModuleConfigObject KAZA_SWERVE_MODULE_BACK_LEFT = new SwerveModuleConfigObject(2, 8, 1, false);
	public static final SwerveModuleConfigObject KAZA_SWERVE_MODULE_BACK_RIGHT = new SwerveModuleConfigObject(12, 5, 3, true);
	public static final double ANGULAR_GEAR_RATIO = 6.0;
	public static final double LINEAR_GEAR_RATIO = 8.0;
	
	public static final double ks = 0.14876;
	public static final double kv = 3.3055;
	public static final double ka = 0.11023;

	public static final double WHEEL_RADIUS = 0.0517;
	public static final double WHEEL_CIRCUMFERENCE = WHEEL_RADIUS * 2 * Math.PI;
	public static final double LINEAR_MOTOR_POSITION_CONVERSION_FACTOR = RobotConstants.General.Motors.SPARKMAX_TICKS_PER_RADIAN * WHEEL_CIRCUMFERENCE / LINEAR_GEAR_RATIO / (2 * Math.PI);
	public static final double ANGULAR_TICKS_TO_WHEEL_TO_RPM = RobotConstants.General.Motors.SPARKMAX_VELOCITY_UNITS_PER_RPM / ANGULAR_GEAR_RATIO;
	public static final double LINEAR_TICKS_TO_METERS_PER_SECOND = RobotConstants.General.Motors.SPARKMAX_VELOCITY_UNITS_PER_RPM * WHEEL_CIRCUMFERENCE / 60 / LINEAR_GEAR_RATIO;
	public static final PIDObject LINEAR_PID = new PIDObject().withKp(0.03).withMaxPower(0.5);
	public static final GBSparkMax.SparkMaxConfObject BASE_LINEAR_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
			.withIdleMode(CANSparkMax.IdleMode.kBrake)
			.withCurrentLimit(40)
			.withRampRate(RobotConstants.General.RAMP_RATE_VAL)
			.withPID(LINEAR_PID)
			.withPositionConversionFactor(LINEAR_MOTOR_POSITION_CONVERSION_FACTOR)
			.withVelocityConversionFactor(LINEAR_TICKS_TO_METERS_PER_SECOND);
	public static final double ANGULAR_TICKS_TO_RADIANS = RobotConstants.General.Motors.SPARKMAX_TICKS_PER_RADIAN / ANGULAR_GEAR_RATIO;
	public static final PIDObject ANGULAR_PID = new PIDObject().withKp(0.5).withMaxPower(1.0);
	public static final GBSparkMax.SparkMaxConfObject BASE_ANGULAR_MOTOR_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
			.withIdleMode(CANSparkMax.IdleMode.kBrake)
			.withCurrentLimit(30)
			.withRampRate(RobotConstants.General.RAMP_RATE_VAL)
			.withInverted(true)
			.withPID(ANGULAR_PID)
			.withPositionConversionFactor(ANGULAR_TICKS_TO_RADIANS)
			.withVelocityConversionFactor(ANGULAR_TICKS_TO_WHEEL_TO_RPM);
	public static final int LAMPREY_AVERAGE_BITS = 2;
	
}
