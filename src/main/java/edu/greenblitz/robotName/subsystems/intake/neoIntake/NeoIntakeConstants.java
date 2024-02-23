
package edu.greenblitz.robotName.subsystems.intake.neoIntake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkLimitSwitch;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class NeoIntakeConstants {

	public static final int MOTOR_ID = 3;

	public static final double DEBOUNCE_TIME_FOR_LIMIT_SWITCH = 0.1;

	public static final SparkLimitSwitch.Type BEAM_BREAKER_TYPE = SparkLimitSwitch.Type.kNormallyOpen;

	public static final boolean IS_BEAM_BREAKER_LIMITING = false;

	public static final int CURRENT_LIMIT = 40;

	public static final GBSparkMax.SparkMaxConfObject INTAKE_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
			.withIdleMode(CANSparkMax.IdleMode.kBrake)
			.withRampRate(RobotConstants.General.RAMP_RATE_VALUE)
			.withCurrentLimit(CURRENT_LIMIT);
}
