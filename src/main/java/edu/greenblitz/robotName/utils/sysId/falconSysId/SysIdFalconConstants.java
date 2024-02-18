package edu.greenblitz.robotName.utils.SysId.falconSysId;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;

public class SysIdFalconConstants {

    protected static final double SIGNAL_SPEED = 250;

    protected static final double DYNAMIC_VOLTAGE = 4;

    protected static final int MOTOR_ID = 16;

    protected static final String CANBUS_CHAIN = "";

    protected static final SoftwareLimitSwitchConfigs LIMIT_SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();
    static {
        LIMIT_SWITCH_CONFIGS.ForwardSoftLimitEnable = false;
        LIMIT_SWITCH_CONFIGS.ForwardSoftLimitThreshold = 187;
        LIMIT_SWITCH_CONFIGS.ReverseSoftLimitEnable = false;
        LIMIT_SWITCH_CONFIGS.ReverseSoftLimitThreshold = -187;
    }

    protected static final FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();
    static{
        FEEDBACK_CONFIGS.SensorToMechanismRatio = 36 / 42.0;
    }
}
