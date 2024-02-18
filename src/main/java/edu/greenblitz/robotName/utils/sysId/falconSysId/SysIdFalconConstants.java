package edu.greenblitz.robotName.utils.SysId.falconSysId;

import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;

public class SysIdFalconConstants {

    protected static final double SIGNAL_SPEED = 250;

    protected static final double DYNAMIC_VOLTAGE = 4;

    protected static final int MOTOR_ID = 187;

    protected static final String CANBUS_CHAIN = "*";

    protected static final SoftwareLimitSwitchConfigs LIMIT_SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();
    static {
        LIMIT_SWITCH_CONFIGS.ForwardSoftLimitEnable = true;
        LIMIT_SWITCH_CONFIGS.ForwardSoftLimitThreshold = 187;
        LIMIT_SWITCH_CONFIGS.ReverseSoftLimitEnable = true;
        LIMIT_SWITCH_CONFIGS.ReverseSoftLimitThreshold = -187;
    }
}
