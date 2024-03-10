package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.falconFlyWheel;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.RobotConstants;

public class FalconFlyWheelConstants {
	
	public static class rightMotorConstants {
		
		public static final int MOTOR_ID = 16;

		public static final String CANBUS_CHAIN = RobotConstants.General.CANBUS_NAME;

		public static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Coast;
		
		public static final int PID_SLOT = 0;
		
		public static final boolean ENABLE_FOC = true;
		
		public static final Slot0Configs SLOT_0_CONFIGS = new Slot0Configs();
		
		static {
			SLOT_0_CONFIGS.kS = 0.26603;
			SLOT_0_CONFIGS.kV = 0.11306;
			SLOT_0_CONFIGS.kA = 0.027703;
			SLOT_0_CONFIGS.kP = 1;
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
		
		public static final FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();
		
		static {
			FEEDBACK_CONFIGS.SensorToMechanismRatio = 40 / 36.0; // old gearbox: 36 / 42.0;
		}
		
		public static final TalonFXConfiguration CONFIGURATION = new TalonFXConfiguration();
		
		static {
			CONFIGURATION.CurrentLimits = CURRENT_LIMITS_CONFIGS;
			CONFIGURATION.Slot0 = SLOT_0_CONFIGS;
			CONFIGURATION.Feedback = FEEDBACK_CONFIGS;
		}
	}
	
	public static class leftMotorConstants {
		
		public static final int MOTOR_ID = 22;

		public static final String CANBUS_CHAIN = RobotConstants.General.CANBUS_NAME;

		public static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Coast;
		
		public static final int PID_SLOT = 0;
		
		public static final boolean ENABLE_FOC = true;
		
		public static final Slot0Configs SLOT_0_CONFIGS = new Slot0Configs();
		
		static {
			SLOT_0_CONFIGS.kS = 0.18387;
			SLOT_0_CONFIGS.kV = 0.11236;
			SLOT_0_CONFIGS.kA = 0.030816;
			SLOT_0_CONFIGS.kP = 1.6;
			SLOT_0_CONFIGS.kI = 0.1;
			SLOT_0_CONFIGS.kD = 0;
		}
		
		public static final FeedbackConfigs FEEDBACK_CONFIGS = new FeedbackConfigs();
		
		static {
			FEEDBACK_CONFIGS.SensorToMechanismRatio = 36 / 42.0;
		}
		
		public static final CurrentLimitsConfigs CURRENT_LIMITS_CONFIGS = new CurrentLimitsConfigs();
		
		static {
			CURRENT_LIMITS_CONFIGS.StatorCurrentLimit = 40;
			CURRENT_LIMITS_CONFIGS.StatorCurrentLimitEnable = true;
			CURRENT_LIMITS_CONFIGS.SupplyCurrentLimit = 40;
			CURRENT_LIMITS_CONFIGS.SupplyCurrentLimitEnable = true;
		}
		
		public static final TalonFXConfiguration CONFIGURATION = new TalonFXConfiguration();
		
		static {
			CONFIGURATION.CurrentLimits = CURRENT_LIMITS_CONFIGS;
			CONFIGURATION.Feedback = FEEDBACK_CONFIGS;
			CONFIGURATION.Slot0 = SLOT_0_CONFIGS;
		}
	}
}