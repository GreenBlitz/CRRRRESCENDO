package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FalconFlyWheel;

import com.ctre.phoenix6.configs.ClosedLoopRampsConfigs;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class FalconFlyWheelConstants {

    public static class rightMotorConstants{
        public static final int ID = 0;
        public static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Coast;

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

        public static final TalonFXConfiguration CONFIGURATION = new TalonFXConfiguration();
        static {
            CONFIGURATION.CurrentLimits = CURRENT_LIMITS_CONFIGS;
            CONFIGURATION.ClosedLoopRamps = CLOSED_LOOP_RAMPS_CONFIGS;
            CONFIGURATION.MotionMagic = MOTION_MAGIC_CONFIGS;
        }
    }

    public static class leftMotorConstants{
        public static final int ID = 0;
        public static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Coast;

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
        public static final TalonFXConfiguration CONFIGURATION = new TalonFXConfiguration();
        static {
            CONFIGURATION.CurrentLimits = CURRENT_LIMITS_CONFIGS;
            CONFIGURATION.ClosedLoopRamps = CLOSED_LOOP_RAMPS_CONFIGS;
            CONFIGURATION.MotionMagic = MOTION_MAGIC_CONFIGS;
        }

    }
}
