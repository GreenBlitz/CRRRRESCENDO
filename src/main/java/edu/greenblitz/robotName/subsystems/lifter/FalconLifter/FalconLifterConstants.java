package edu.greenblitz.robotName.subsystems.lifter.FalconLifter;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.ReverseLimitTypeValue;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.lifter.neoLifter.NeoLifterConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.math.util.Units;

public class FalconLifterConstants {
	
	public static final int MOTOR_ID = 10;
	
	public static final String CANBUS_CHAIN = RobotConstants.General.CANIVORE_NAME;
	
	public static final boolean INVERTED = true;
	
	public static final double kS = 0;
	
	public static final double kV = 0;
	
	public static final double kA = 0;
	
	public static final double kG = 0;
	
	public static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;
	
	public static final int PID_SLOT = 0;
	
	public static final double GEAR_RATIO = 1;
	
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
	
	public static final SoftwareLimitSwitchConfigs SOFTWARE_LIMIT_SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();
	
	static {
		SOFTWARE_LIMIT_SWITCH_CONFIGS.ForwardSoftLimitEnable = true;
		SOFTWARE_LIMIT_SWITCH_CONFIGS.ForwardSoftLimitThreshold = LifterConstants.FORWARD_LIMIT.getRotations();
		SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitEnable = true;
		SOFTWARE_LIMIT_SWITCH_CONFIGS.ReverseSoftLimitThreshold = LifterConstants.BACKWARD_LIMIT.getRotations();
	}
	
	public static final FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();
	
	static {
		FEEDBACK_CONFIGS.SensorToMechanismRatio = GEAR_RATIO;
	}
	
	public static final HardwareLimitSwitchConfigs HARDWARE_LIMIT_SWITCH_CONFIGS = new HardwareLimitSwitchConfigs();
	
	static {
		HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitEnable = false;
		HARDWARE_LIMIT_SWITCH_CONFIGS.ReverseLimitType = ReverseLimitTypeValue.NormallyOpen;
		HARDWARE_LIMIT_SWITCH_CONFIGS.ForwardLimitEnable = false;
	}
	
	public static final  ClosedLoopRampsConfigs CLOSED_LOOP_RAMPS_CONFIGS = new ClosedLoopRampsConfigs();
	
	static {
		CLOSED_LOOP_RAMPS_CONFIGS.VoltageClosedLoopRampPeriod = 0.5;
	}
	
	public static final TalonFXConfiguration TALON_FX_CONFIGURATION = new TalonFXConfiguration();
	
	static {
		TALON_FX_CONFIGURATION.ClosedLoopRamps = CLOSED_LOOP_RAMPS_CONFIGS;
		TALON_FX_CONFIGURATION.SoftwareLimitSwitch = SOFTWARE_LIMIT_SWITCH_CONFIGS;
		TALON_FX_CONFIGURATION.Slot0 = SLOT_0_CONFIGS;
		TALON_FX_CONFIGURATION.Feedback = FEEDBACK_CONFIGS;
		TALON_FX_CONFIGURATION.HardwareLimitSwitch = HARDWARE_LIMIT_SWITCH_CONFIGS;
	}
}
