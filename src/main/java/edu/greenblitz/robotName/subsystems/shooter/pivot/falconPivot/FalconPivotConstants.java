package edu.greenblitz.robotName.subsystems.shooter.pivot.falconPivot;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.ReverseLimitTypeValue;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.math.util.Units;


public class FalconPivotConstants {
	
	public static final int MOTOR_ID = 11;

	public static final String CANBUS_CHANNEL = "*";
	
	public static final int ABSOLUTE_ENCODER_CHANNEL = 1;

	public static final double ABSOLUTE_ENCODER_OFFSET = 0.855 - Units.degreesToRotations(16);

	public static final boolean INVERTED = true;

	public static final double kS = 0;
	
	public static final double kV = 0;
	
	public static final double kA = 0;
	
	public static final double kG = 0;
	
	public static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Coast;
	
	public static final int PID_SLOT = 0;
	
	public static final Slot0Configs SLOT_0_CONFIGS = new Slot0Configs();
	
	static {
		SLOT_0_CONFIGS.GravityType = GravityTypeValue.Arm_Cosine;
		SLOT_0_CONFIGS.kG = kG;
		SLOT_0_CONFIGS.kS = kS;
		SLOT_0_CONFIGS.kA = kA;
		SLOT_0_CONFIGS.kV = kV;
		SLOT_0_CONFIGS.kP = 0;
		SLOT_0_CONFIGS.kI = 0;
		SLOT_0_CONFIGS.kD = 0;
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
		SOFTWARE_LIMIT_SWITCH_CONFIGS.ForwardSoftLimitThreshold = PivotConstants.FORWARD_ANGLE_LIMIT.getRotations();
		SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitEnable = false;
		SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitThreshold = PivotConstants.BACKWARD_ANGLE_LIMIT.getRotations();
	}
	
	public static final FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();
	static {
		FEEDBACK_CONFIGS.SensorToMechanismRatio = PivotConstants.GEAR_RATIO;
	}

	public static final HardwareLimitSwitchConfigs HARDWARE_LIMIT_SWITCH_CONFIGS = new HardwareLimitSwitchConfigs();
	static {
		HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitEnable = true;
		HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitType = ReverseLimitTypeValue.NormallyOpen;
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