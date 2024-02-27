package edu.greenblitz.robotName.utils.SysId.falconSysId;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.HardwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.signals.ReverseLimitTypeValue;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.modules.mk4iSwerveModule.MK4iSwerveConstants;

public class SysIdFalconConstants {
	
	protected static final double SIGNAL_SPEED = 250;
	
	protected static final double DYNAMIC_VOLTAGE = 1;
	
	protected static final int MOTOR_ID = 5;
	
	protected static final String CANBUS_CHAIN = RobotConstants.General.CANIVORE_NAME;
	
	public static final SoftwareLimitSwitchConfigs SOFTWARE_LIMIT_SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();
	
	public static final HardwareLimitSwitchConfigs HARDWARE_LIMIT_SWITCH_CONFIGS = new HardwareLimitSwitchConfigs();
	
	public static final FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();
	
	static {
		FEEDBACK_CONFIGS.SensorToMechanismRatio = MK4iSwerveConstants.ANGULAR_GEAR_RATIO;
	}
}
