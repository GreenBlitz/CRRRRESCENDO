package edu.greenblitz.robotName.subsystems.lifter.falconLifter;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.ReverseLimitTypeValue;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;

public class FalconLifterConstants {

    public static final int MOTOR_ID = 12;

    public static final String CANBUS_CHANNEL = RobotConstants.General.CANIVORE_NAME;

    public static final boolean INVERTED = false;

    public static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Coast;

    public static final int PID_SLOT = 0;

    public static Slot0Configs SLOT_0_CONFIGS = new Slot0Configs();
    static {
        SLOT_0_CONFIGS.kG = 0;
        SLOT_0_CONFIGS.kS = 0;
        SLOT_0_CONFIGS.kP = 0;
        SLOT_0_CONFIGS.kI = 0;
        SLOT_0_CONFIGS.kD = 0;
    }

    public static final CurrentLimitsConfigs CURRENT_LIMITS_CONFIGS = new CurrentLimitsConfigs();
    static {
        CURRENT_LIMITS_CONFIGS.StatorCurrentLimit = 40;
        CURRENT_LIMITS_CONFIGS.StatorCurrentLimitEnable = false;
        CURRENT_LIMITS_CONFIGS.SupplyCurrentLimit = 40;
        CURRENT_LIMITS_CONFIGS.SupplyCurrentLimitEnable = false;
    }

    public static final SoftwareLimitSwitchConfigs SOFTWARE_LIMIT_SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();
    static {
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ForwardSoftLimitEnable = true;
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ForwardSoftLimitThreshold = LifterConstants.FORWARD_LIMIT;
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitEnable = true;
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitThreshold = LifterConstants.BACKWARD_LIMIT;
    }

    public static final FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();
    static {
        FEEDBACK_CONFIGS.SensorToMechanismRatio = LifterConstants.GEAR_RATIO;
    }

    public static final HardwareLimitSwitchConfigs HARDWARE_LIMIT_SWITCH_CONFIGS = new HardwareLimitSwitchConfigs();
    static {
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitEnable = false;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitType = ReverseLimitTypeValue.NormallyOpen;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ForwardLimitEnable = false;
    }

    public static final TalonFXConfiguration TALON_FX_CONFIGURATION = new TalonFXConfiguration();
    static {
        TALON_FX_CONFIGURATION.CurrentLimits = CURRENT_LIMITS_CONFIGS;
        TALON_FX_CONFIGURATION.SoftwareLimitSwitch = SOFTWARE_LIMIT_SWITCH_CONFIGS;
        TALON_FX_CONFIGURATION.Slot0 = SLOT_0_CONFIGS;
        TALON_FX_CONFIGURATION.Feedback = FEEDBACK_CONFIGS;
        TALON_FX_CONFIGURATION.HardwareLimitSwitch = HARDWARE_LIMIT_SWITCH_CONFIGS;
    }


}
