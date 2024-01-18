package edu.greenblitz.robotName.subsystems.Arm.Pivot;

import com.ctre.phoenix6.configs.ClosedLoopRampsConfigs;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class PivotConstants {
    public static final double kS = 1;
    public static final double kV = 1;
    public static final double kG = 1;
    public static final double kA = 1;
    public static final double TOLERANCE = 0.5;
    public static final double LENGTH_OF_SHOOTER = 0.2;
    public static final double SHOOTER_MASS_KG = 10;
    public static final double RELATIVE_POSITION_CONVERSION_FACTOR = 0.0328; //you know of calibrating pid but have you heard of calibrating the gear ratio
    public static final double RELATIVE_VELOCITY_CONVERSION_FACTOR = 0.0030332432;
    public static final double ABSOLUTE_POSITION_CONVERSION_FACTOR = 0.00302;
    public static final double BACKWARD_ANGLE_LIMIT = Double.MIN_VALUE;
    public static final double FORWARD_ANGLE_LIMIT = Double.MAX_VALUE;
    public static final int MOTOR_ID = 1;

    public static class Simulation {
        public static final double STARTING_ANGLE = 90;//in radians
        public static final PIDController SIM_PID = new PIDController(1,0,0);
        public static final int NUMBER_OF_MOTORS = 1;
        public static final double GEAR_RATIO = 1 / RELATIVE_POSITION_CONVERSION_FACTOR;
    }
    public static class FalconConfigs {
        public static final SimpleMotorFeedforward SIMPLE_MOTOR_FF = new SimpleMotorFeedforward(kS, kV, kA);

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

        public static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;

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
            SWITCH_CONFIGS.ForwardSoftLimitThreshold = 1;
            SWITCH_CONFIGS.ReverseSoftLimitEnable = true;
            SWITCH_CONFIGS.ReverseSoftLimitThreshold = 2;
        }
    }
}
