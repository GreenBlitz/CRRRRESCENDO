package edu.greenblitz.robotName.utils.SysId.falconSysId;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.HardwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.signals.ReverseLimitTypeValue;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;

public class SysIdFalconConstants {

    protected static final double SIGNAL_SPEED = 250;

    protected static final double DYNAMIC_VOLTAGE = 1;

    protected static final int MOTOR_ID = 11;

    protected static final String CANBUS_CHAIN = "*";

    public static final SoftwareLimitSwitchConfigs SOFTWARE_LIMIT_SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();
    static {
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ForwardSoftLimitEnable = false;
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ForwardSoftLimitThreshold = PivotConstants.FORWARD_ANGLE_LIMIT.getRotations();
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitEnable = false;
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitThreshold = PivotConstants.BACKWARD_ANGLE_LIMIT.getRotations();
    }

    public static final HardwareLimitSwitchConfigs HARDWARE_LIMIT_SWITCH_CONFIGS = new HardwareLimitSwitchConfigs();
    static {
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitEnable = false;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitType = ReverseLimitTypeValue.NormallyOpen;
    }

    public static final FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();
    static {
        FEEDBACK_CONFIGS.SensorToMechanismRatio = PivotConstants.GEAR_RATIO;
    }
}
