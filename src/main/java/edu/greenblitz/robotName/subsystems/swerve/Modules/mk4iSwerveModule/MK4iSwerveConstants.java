package edu.greenblitz.robotName.subsystems.swerve.Modules.mk4iSwerveModule;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.SwerveModuleConfigObject;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBFalcon;
import edu.wpi.first.math.geometry.Rotation2d;

public class MK4iSwerveConstants {
	
	public static SwerveModuleConfigObject MK4I_MODULE_FRONT_LEFT = new SwerveModuleConfigObject(1, 10,0, Rotation2d.fromRotations(0.8486328125),false);
	public static SwerveModuleConfigObject MK4I_MODULE_FRONT_RIGHT = new SwerveModuleConfigObject(3, 11, 2,Rotation2d.fromRotations(0.2939453125) ,true);
	public static SwerveModuleConfigObject MK4I_MODULE_BACK_LEFT = new SwerveModuleConfigObject(2, 8, 1, Rotation2d.fromRotations(0.3024),false);
	public static SwerveModuleConfigObject MK4I_MODULE_BAK_RIGHT = new SwerveModuleConfigObject(12, 5, 3, Rotation2d.fromRotations(0.8718),true);
	
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
	
	public static final PIDObject ANGULAR_PID = new PIDObject().withKp(0.05).withMaxPower(1.0).withFF(0);
	public static final GBFalcon.FalconConfObject ANGULAR_FALCON_CONFIG_OBJECT = new GBFalcon.FalconConfObject().withNeutralMode(NeutralMode.Brake).withCurrentLimit(30).withRampRate(RobotConstants.General.RAMP_RATE_VAL).withInverted(true).withPID(ANGULAR_PID);
	
	public static final PIDObject LINEAR_PID = new PIDObject().withKp(0.0003).withMaxPower(0.5);
	public static final GBFalcon.FalconConfObject LINEAR_FALCON_CONF_OBJECT = new GBFalcon.FalconConfObject()
			.withNeutralMode(NeutralMode.Brake)
			.withCurrentLimit(40)
			.withRampRate(RobotConstants.General.RAMP_RATE_VAL)
			.withPID(LINEAR_PID);
	public static final double LINEAR_MOTOR_FREE_CURRENT = 1.5;
}
