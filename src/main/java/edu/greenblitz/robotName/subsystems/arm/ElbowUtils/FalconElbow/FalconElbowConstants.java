package edu.greenblitz.robotName.subsystems.arm.ElbowUtils.FalconElbow;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.signals.ForwardLimitTypeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.ReverseLimitTypeValue;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import static edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants.*;

public class FalconElbowConstants {

    public static final int FALCON_MOTOR_ID = 1;

    public static final int HARD_REVERSE_LIMIT_SWITCH_ID = 1;

    public static final int HARD_FORWARD_LIMIT_SWITCH_ID = 1;

    public static final double kS = 1;

    public static final double kV = 1;

    public static final double kG = 1;

    public static final double kA = 1;

    public static final SimpleMotorFeedforward SIMPLE_MOTOR_FEED_FORWARD = new SimpleMotorFeedforward(kS, kV, kA);

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

    public static final SoftwareLimitSwitchConfigs SOFTWARE_LIMIT_SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();

    static {
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ForwardSoftLimitEnable = true;
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ForwardSoftLimitThreshold = FORWARD_ANGLE_LIMIT.getRadians();
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitEnable = true;
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitThreshold = BACKWARD_ANGLE_LIMIT.getRadians();
    }

    public static final HardwareLimitSwitchConfigs HARDWARE_LIMIT_SWITCH_CONFIGS = new HardwareLimitSwitchConfigs();
    static{
        HARDWARE_LIMIT_SWITCH_CONFIGS.ForwardLimitAutosetPositionEnable = true;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ForwardLimitAutosetPositionValue = FORWARD_ANGLE_LIMIT.getRadians();
        HARDWARE_LIMIT_SWITCH_CONFIGS.ForwardLimitEnable = true;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ForwardLimitType = ForwardLimitTypeValue.NormallyOpen;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ForwardLimitRemoteSensorID = HARD_FORWARD_LIMIT_SWITCH_ID;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitAutosetPositionEnable = true;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitAutosetPositionValue = FORWARD_ANGLE_LIMIT.getRadians();
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitEnable = true;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitType = ReverseLimitTypeValue.NormallyOpen;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitRemoteSensorID = HARD_REVERSE_LIMIT_SWITCH_ID;
    }

    public static final TalonFXConfiguration TALON_FX_CONFIGURATION = new TalonFXConfiguration();

    static{
        TALON_FX_CONFIGURATION.MotionMagic = MOTION_MAGIC_CONFIGS;
        TALON_FX_CONFIGURATION.ClosedLoopRamps = CLOSED_LOOP_RAMPS_CONFIGS;
        TALON_FX_CONFIGURATION.CurrentLimits = CURRENT_LIMITS_CONFIGS;
        TALON_FX_CONFIGURATION.SoftwareLimitSwitch = SOFTWARE_LIMIT_SWITCH_CONFIGS;
        TALON_FX_CONFIGURATION.HardwareLimitSwitch = HARDWARE_LIMIT_SWITCH_CONFIGS;
    }



}
