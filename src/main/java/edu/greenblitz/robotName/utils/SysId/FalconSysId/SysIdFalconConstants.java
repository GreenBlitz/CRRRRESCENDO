package edu.greenblitz.robotName.utils.SysId.FalconSysId;

import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;

public class SysIdFalconConstants {

    public static final double SIGNAL_SPEED = 250;

    public static final double DYNAMIC_VOLTAGE = 4;

    public static final int MOTOR_ID = 187;

    public static final SoftwareLimitSwitchConfigs LIMIT_SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();
    static {
        LIMIT_SWITCH_CONFIGS.ForwardSoftLimitEnable = true;
        LIMIT_SWITCH_CONFIGS.ForwardSoftLimitThreshold = 187;
        LIMIT_SWITCH_CONFIGS.ReverseSoftLimitEnable = true;
        LIMIT_SWITCH_CONFIGS.ReverseSoftLimitThreshold = -187;
    }
}
