
package edu.greenblitz.robotName.subsystems.intake.neoIntake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkLimitSwitch;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class NeoIntakeConstants {

	public static final int MOTOR_ID = 3;
	
	public static final double kS = 0.23;
	
	public static final double kV = 0.022505;
	
	public static final double kA = 0.06;
	
	public static final SimpleMotorFeedforward SIMPLE_MOTOR_FEEDFORWARD = new SimpleMotorFeedforward(kS, kV, kA);
	public static final PIDObject PID_CONTROLLER = new PIDObject(2, 0, 0);

	public static final double DEBOUNCE_TIME_FOR_LIMIT_SWITCH = 0.1;

	public static final SparkLimitSwitch.Type BEAM_BREAKER_TYPE = SparkLimitSwitch.Type.kNormallyOpen;

	public static final boolean IS_BEAM_BREAKER_LIMITING = false;

	public static final int CURRENT_LIMIT = 40;

	public static final GBSparkMax.SparkMaxConfObject INTAKE_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
			. withIdleMode(CANSparkMax.IdleMode.kBrake)
			.withRampRate(RobotConstants.General.RAMP_RATE_VALUE)
			.withCurrentLimit(CURRENT_LIMIT)
			.withPID(PID_CONTROLLER)
			.withVelocityConversionFactor(1 / 10.0);
}
