package edu.greenblitz.robotName.subsystems.Arm.ElbowUtils.FalconElbow;

import com.ctre.phoenix6.configs.ClosedLoopRampsConfigs;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import static edu.greenblitz.robotName.subsystems.Arm.ElbowUtils.ElbowConstants.*;

public class FalconElbowConstants {

    public static final int MOTOR_ID = 1;

    public static final SimpleMotorFeedforward SIMPLE_MOTOR_FF = new SimpleMotorFeedforward(kS, kV, kA);

    public static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;

    public static final MotionMagicConfigs MOTION_MAGIC_CONFIGS = new MotionMagicConfigs();
    static {
        MOTION_MAGIC_CONFIGS.MotionMagicAcceleration = 1;
        MOTION_MAGIC_CONFIGS.MotionMagicCruiseVelocity = 2;
        MOTION_MAGIC_CONFIGS.MotionMagicJerk = 4;
    }

    public static final ClosedLoopRampsConfigs CLOSED_LOOP_RAMPS_CONFIGS = new ClosedLoopRampsConfigs();
    static {
        CLOSED_LOOP_RAMPS_CONFIGS.DutyCycleClosedLoopRampPeriod = 1;
        CLOSED_LOOP_RAMPS_CONFIGS.TorqueClosedLoopRampPeriod = 2;
        CLOSED_LOOP_RAMPS_CONFIGS.VoltageClosedLoopRampPeriod = 3;
    }

    public static final CurrentLimitsConfigs CURRENT_LIMITS_CONFIGS = new CurrentLimitsConfigs();
    static {
        CURRENT_LIMITS_CONFIGS.StatorCurrentLimit = 1;
        CURRENT_LIMITS_CONFIGS.StatorCurrentLimitEnable = true;
        CURRENT_LIMITS_CONFIGS.SupplyCurrentLimit = 2;
        CURRENT_LIMITS_CONFIGS.SupplyCurrentLimitEnable = true;
        CURRENT_LIMITS_CONFIGS.SupplyCurrentThreshold = 3;
        CURRENT_LIMITS_CONFIGS.SupplyTimeThreshold = 4;
    }

    public static final SoftwareLimitSwitchConfigs SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();
    static {
        SWITCH_CONFIGS.ForwardSoftLimitEnable = true;
        SWITCH_CONFIGS.ForwardSoftLimitThreshold = FORWARD_ANGLE_LIMIT;
        SWITCH_CONFIGS.ReverseSoftLimitEnable = true;
        SWITCH_CONFIGS.ReverseSoftLimitThreshold = BACKWARD_ANGLE_LIMIT;
    }

}
