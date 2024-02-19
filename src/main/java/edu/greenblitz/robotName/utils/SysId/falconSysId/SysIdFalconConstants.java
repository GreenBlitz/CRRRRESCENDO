package edu.greenblitz.robotName.utils.SysId.falconSysId;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;

public class SysIdFalconConstants {

    protected static final double SIGNAL_SPEED = 250;

    protected static final double DYNAMIC_VOLTAGE = 4;

    protected static final int MOTOR_ID = 22;

    protected static final String CANBUS_CHAIN = "";

    protected static final SoftwareLimitSwitchConfigs LIMIT_SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();
    static {
        LIMIT_SWITCH_CONFIGS.ForwardSoftLimitEnable = true;
        LIMIT_SWITCH_CONFIGS.ForwardSoftLimitThreshold = -0.2;
        LIMIT_SWITCH_CONFIGS.ReverseSoftLimitEnable = true;
        LIMIT_SWITCH_CONFIGS.ReverseSoftLimitThreshold = 0.5;
    }

    protected static final FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();
    static{
        FEEDBACK_CONFIGS.SensorToMechanismRatio = 36 / 42.0;
    }
}
