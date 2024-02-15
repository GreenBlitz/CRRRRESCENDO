package edu.greenblitz.robotName.subsystems.arm.elbow.falconElbow;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.signals.ForwardLimitTypeValue;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.ReverseLimitTypeValue;
import edu.wpi.first.math.controller.ArmFeedforward;

import static edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants.*;

public class FalconElbowConstants {

    public static final int MOTOR_ID = 23;

    public static final int ABSOLUTE_ENCODER_CHANNEL = 9;

    public static final int HARD_REVERSE_LIMIT_SWITCH_ID = 1;

    public static final int HARD_FORWARD_LIMIT_SWITCH_ID = 1;

    public static final double kS = 1;

    public static final double kV = 1;

    public static final double kG = 1;

    public static final double kA = 1;

    public static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;

    public static final int MOTION_MAGIC_PID_SLOT = 1;
    public static final int STAND_IN_PLACE_PID_SLOT = 0;

    public static final Slot0Configs SLOT_0_CONFIGS = new Slot0Configs();
    static{
        SLOT_0_CONFIGS.kG = kG;
        SLOT_0_CONFIGS.kS = kS;
        SLOT_0_CONFIGS.kA = kA;
        SLOT_0_CONFIGS.kV = kV;
        SLOT_0_CONFIGS.GravityType = GravityTypeValue.Arm_Cosine;
        SLOT_0_CONFIGS.kP = 1;
        SLOT_0_CONFIGS.kI = 0;
        SLOT_0_CONFIGS.kD = 0;
    }


    public static final MotionMagicConfigs MOTION_MAGIC_CONFIGS = new MotionMagicConfigs();
    static {
        MOTION_MAGIC_CONFIGS.MotionMagicAcceleration = 1;
        MOTION_MAGIC_CONFIGS.MotionMagicCruiseVelocity = 2;
        MOTION_MAGIC_CONFIGS.MotionMagicJerk = 4;
    }

    public static final CurrentLimitsConfigs CURRENT_LIMITS_CONFIGS = new CurrentLimitsConfigs();

    static {
        CURRENT_LIMITS_CONFIGS.StatorCurrentLimit = 40;
        CURRENT_LIMITS_CONFIGS.StatorCurrentLimitEnable = true;
        CURRENT_LIMITS_CONFIGS.SupplyCurrentLimit = 40;
        CURRENT_LIMITS_CONFIGS.SupplyCurrentLimitEnable = true;
    }

    public static final SoftwareLimitSwitchConfigs SOFTWARE_LIMIT_SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();

    static {
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ForwardSoftLimitEnable = true;
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ForwardSoftLimitThreshold = FORWARD_ANGLE_LIMIT.getRotations();
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitEnable = true;
        SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitThreshold = BACKWARD_ANGLE_LIMIT.getRotations();
    }

    public static final HardwareLimitSwitchConfigs HARDWARE_LIMIT_SWITCH_CONFIGS = new HardwareLimitSwitchConfigs();

    static {
        HARDWARE_LIMIT_SWITCH_CONFIGS.ForwardLimitAutosetPositionEnable = true;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ForwardLimitAutosetPositionValue = FORWARD_ANGLE_LIMIT.getRotations();
        HARDWARE_LIMIT_SWITCH_CONFIGS.ForwardLimitEnable = true;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ForwardLimitType = ForwardLimitTypeValue.NormallyOpen;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ForwardLimitRemoteSensorID = HARD_FORWARD_LIMIT_SWITCH_ID;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitAutosetPositionEnable = true;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitAutosetPositionValue = FORWARD_ANGLE_LIMIT.getRotations();
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitEnable = true;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitType = ReverseLimitTypeValue.NormallyOpen;
        HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitRemoteSensorID = HARD_REVERSE_LIMIT_SWITCH_ID;
    }

    public static final FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();
    static{
        FEEDBACK_CONFIGS.SensorToMechanismRatio = GEAR_RATIO;
    }


    public static final TalonFXConfiguration TALON_FX_CONFIGURATION = new TalonFXConfiguration();
    static {
        TALON_FX_CONFIGURATION.MotionMagic = MOTION_MAGIC_CONFIGS;
        TALON_FX_CONFIGURATION.CurrentLimits = CURRENT_LIMITS_CONFIGS;
        TALON_FX_CONFIGURATION.SoftwareLimitSwitch = SOFTWARE_LIMIT_SWITCH_CONFIGS;
        TALON_FX_CONFIGURATION.HardwareLimitSwitch = HARDWARE_LIMIT_SWITCH_CONFIGS;
    }
}