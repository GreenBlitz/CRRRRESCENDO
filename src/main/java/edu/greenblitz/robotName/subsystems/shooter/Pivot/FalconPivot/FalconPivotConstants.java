package edu.greenblitz.robotName.subsystems.shooter.Pivot.FalconPivot;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.configs.ClosedLoopRampsConfigs;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.wpi.first.math.controller.ArmFeedforward;


public class FalconPivotConstants {

    public static final int MOTOR_ID = 33;

    public static final double kS = 1;

    public static final double kV = 1;

    public static final double kA = 1;

    public static final double kG = 1;

    public static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;

    public static final int MOTION_MAGIC_PID_SLOT = 1;

    public static final int STAND_IN_PLACE_PID_SLOT = 0;

    public static final Slot0Configs SLOT_0_CONFIGS = new Slot0Configs();
    static{
        SLOT_0_CONFIGS.GravityType = GravityTypeValue.Arm_Cosine;
        SLOT_0_CONFIGS.kG = kG;
        SLOT_0_CONFIGS.kS = kS;
        SLOT_0_CONFIGS.kA = kA;
        SLOT_0_CONFIGS.kV = kV;
        SLOT_0_CONFIGS.kP = 0.3;
        SLOT_0_CONFIGS.kI = 0;
        SLOT_0_CONFIGS.kD = 0;
    }

    public static final MotionMagicConfigs MOTION_MAGIC_CONFIGS = new MotionMagicConfigs();
    static {
        MOTION_MAGIC_CONFIGS.MotionMagicAcceleration = kA;
        MOTION_MAGIC_CONFIGS.MotionMagicCruiseVelocity = kV;
        MOTION_MAGIC_CONFIGS.MotionMagicJerk = 4; //broder saying that this is not important
    }

    public static final ClosedLoopRampsConfigs CLOSED_LOOP_RAMPS_CONFIGS = new ClosedLoopRampsConfigs();
    static {
        CLOSED_LOOP_RAMPS_CONFIGS.DutyCycleClosedLoopRampPeriod = 20;
        CLOSED_LOOP_RAMPS_CONFIGS.TorqueClosedLoopRampPeriod = 20;
        CLOSED_LOOP_RAMPS_CONFIGS.VoltageClosedLoopRampPeriod = 20;
    }

    public static final CurrentLimitsConfigs CURRENT_LIMITS_CONFIGS = new CurrentLimitsConfigs();
    static {
        CURRENT_LIMITS_CONFIGS.StatorCurrentLimit = 40;
        CURRENT_LIMITS_CONFIGS.StatorCurrentLimitEnable = true;
        CURRENT_LIMITS_CONFIGS.SupplyCurrentLimit = 40;
        CURRENT_LIMITS_CONFIGS.SupplyCurrentLimitEnable = true;
        CURRENT_LIMITS_CONFIGS.SupplyCurrentThreshold = 40;
        CURRENT_LIMITS_CONFIGS.SupplyTimeThreshold = 0.2;
    }

    public static final SoftwareLimitSwitchConfigs SOFTWARE_LIMIT_SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();
    static {
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ForwardSoftLimitEnable = true;
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ForwardSoftLimitThreshold = PivotConstants.FORWARD_ANGLE_LIMIT.getRotations();
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitEnable = true;
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitThreshold = PivotConstants.BACKWARD_ANGLE_LIMIT.getRotations();
    }

    //TODO - go through with noam on set real numbers in configs
    public static final TalonFXConfiguration TALON_FX_CONFIGURATION = new TalonFXConfiguration();
    static{
        TALON_FX_CONFIGURATION.MotionMagic = MOTION_MAGIC_CONFIGS;
        TALON_FX_CONFIGURATION.ClosedLoopRamps = CLOSED_LOOP_RAMPS_CONFIGS;
        TALON_FX_CONFIGURATION.CurrentLimits = CURRENT_LIMITS_CONFIGS;
        TALON_FX_CONFIGURATION.SoftwareLimitSwitch = SOFTWARE_LIMIT_SWITCH_CONFIGS;
        TALON_FX_CONFIGURATION.Slot0 = SLOT_0_CONFIGS;
    }
}
