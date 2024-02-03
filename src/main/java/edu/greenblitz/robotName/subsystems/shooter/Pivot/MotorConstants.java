package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;

import static edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants.BACKWARD_ANGLE_LIMIT;
import static edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants.FORWARD_ANGLE_LIMIT;

public class MotorConstants {
	
	public static final int MOTOR_ID = 33;
	
	public static final SoftwareLimitSwitchConfigs SWITCH_CONFIGS = new SoftwareLimitSwitchConfigs();
	static {
		SWITCH_CONFIGS.ForwardSoftLimitEnable = false;
		SWITCH_CONFIGS.ForwardSoftLimitThreshold = FORWARD_ANGLE_LIMIT;
		SWITCH_CONFIGS.ReverseSoftLimitEnable = false;
		SWITCH_CONFIGS.ReverseSoftLimitThreshold = BACKWARD_ANGLE_LIMIT;
	}
}
