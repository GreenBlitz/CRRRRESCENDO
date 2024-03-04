package edu.greenblitz.robotName.subsystems.arm.wrist.neoWrist;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.ArmFeedforward;

public class NeoWristConstants {

    public static final int MOTOR_ID = 4;

    public static final int PID_SLOT = 0;

    public static final PIDObject PID = new PIDObject().withKp(0.8).withKd(0.3).withKi(0).withMaxPower(1);

    public static final ArmFeedforward WRIST_FEED_FORWARD = new ArmFeedforward(1, 1, 1, 1);

    public static final GBSparkMax.SparkMaxConfObject WRIST_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
            .withPID(PID)
            .withIdleMode(CANSparkMax.IdleMode.kBrake)
            .withRampRate(RobotConstants.General.RAMP_RATE_VALUE)
            .withCurrentLimit(WristConstants.CURRENT_LIMIT)
            .withPositionConversionFactor(WristConstants.CONVERSION_FACTOR)
            .withVelocityConversionFactor(WristConstants.CONVERSION_FACTOR);

    public static final boolean IS_BACKWARD_LIMIT_SWITCH_ENABLED = false;

    public static final boolean IS_FORWARD_LIMIT_SWITCH_ENABLED = false;

    public static final boolean IS_SOFT_FORWARD_LIMIT_ENABLED = true;

    public static final boolean IS_SOFT_BACKWARD_LIMIT_ENABLED = true;

    public static final int MAX_VOLT = 6;
}