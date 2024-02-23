package edu.greenblitz.robotName.subsystems.arm.wrist.bagWrist;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.utils.Conversions;

public class BagWristConstants {

    public static final int MOTOR_ID = 1;

    public static final int PID_SLOT = 0;

    public static final int TIMEOUT_FOR_CONFIG_SET = 0;

    public static final TalonSRXConfiguration TALON_SRX_CONFIGURATION = new TalonSRXConfiguration();
    static{
        TALON_SRX_CONFIGURATION.forwardSoftLimitEnable = true;
        TALON_SRX_CONFIGURATION.forwardSoftLimitThreshold = Conversions.MagEncoderConversions.Rotation2DToMotorPosition(WristConstants.FORWARD_ANGLE_LIMIT);
        TALON_SRX_CONFIGURATION.reverseSoftLimitEnable = true;
        TALON_SRX_CONFIGURATION.reverseSoftLimitThreshold = Conversions.MagEncoderConversions.Rotation2DToMotorPosition(WristConstants.BACKWARD_ANGLE_LIMIT);
        TALON_SRX_CONFIGURATION.slot0.kP = 1;
        TALON_SRX_CONFIGURATION.slot0.kI = 0;
        TALON_SRX_CONFIGURATION.slot0.kD = 0;
    }


}
