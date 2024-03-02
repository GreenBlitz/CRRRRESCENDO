package edu.greenblitz.robotName.subsystems.arm.wrist.srxWrist;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.utils.Conversions;

public class SRXWristConstants {

    public static final int MOTOR_ID = 11;

    public static final int PID_SLOT = 0;

    public static final int TIMEOUT_FOR_CONFIG_SET = 0;

    public static final double MAG_ENCODER_CONVERSION_FACTOR = 8192; //mag * versa

    public static final TalonSRXConfiguration TALON_SRX_CONFIGURATION = new TalonSRXConfiguration();

    static {
        TALON_SRX_CONFIGURATION.forwardSoftLimitEnable = false;
        TALON_SRX_CONFIGURATION.forwardSoftLimitThreshold = Conversions.MagEncoderConversions.Rotation2DToMotorPosition(WristConstants.FORWARD_ANGLE_LIMIT);
        TALON_SRX_CONFIGURATION.reverseSoftLimitEnable = false;
        TALON_SRX_CONFIGURATION.reverseSoftLimitThreshold = Conversions.MagEncoderConversions.Rotation2DToMotorPosition(WristConstants.BACKWARD_ANGLE_LIMIT);
        TALON_SRX_CONFIGURATION.slot0.kP = 200;
        TALON_SRX_CONFIGURATION.slot0.kI = 0;
        TALON_SRX_CONFIGURATION.slot0.kD = 0;
    }
}
