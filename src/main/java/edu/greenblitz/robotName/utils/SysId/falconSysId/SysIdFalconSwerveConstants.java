package edu.greenblitz.robotName.utils.SysId.falconSysId;

import com.ctre.phoenix6.configs.*;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.modules.mk4iSwerveModule.MK4iSwerveConstants;

public class SysIdFalconSwerveConstants {

    protected static final double SIGNAL_SPEED = 250;

    protected static final double DYNAMIC_VOLTAGE = 0.5;

    protected static final int MOTOR_FRONT_LEFT = 4;

    protected static final int MOTOR_FRONT_RIGHT = 2;

    protected static final int MOTOR_BACK_LEFT = 43;

    protected static final int MOTOR_BACK_RIGHT = 6;

    protected static final String CANBUS_CHAIN = RobotConstants.General.CANIVORE_NAME;

    public static final SoftwareLimitSwitchConfigs SOFTWARE_LIMIT_SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();

    public static final HardwareLimitSwitchConfigs HARDWARE_LIMIT_SWITCH_CONFIGS = new HardwareLimitSwitchConfigs();

    public static final FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();

    static {
        FEEDBACK_CONFIGS.SensorToMechanismRatio = MK4iSwerveConstants.LINEAR_GEAR_RATIO;
    }

    public static final CurrentLimitsConfigs CURRENT_LIMITS_CONFIGS = new CurrentLimitsConfigs();

    static {
        CURRENT_LIMITS_CONFIGS.StatorCurrentLimitEnable = true;
        CURRENT_LIMITS_CONFIGS.StatorCurrentLimit = 40;
    }

    public static final TalonFXConfiguration a = new TalonFXConfiguration();

    static {
        a.CurrentLimits = CURRENT_LIMITS_CONFIGS;
        a.Feedback = FEEDBACK_CONFIGS;
    }
}
