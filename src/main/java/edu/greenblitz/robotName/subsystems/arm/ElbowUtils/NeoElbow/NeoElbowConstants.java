package edu.greenblitz.robotName.subsystems.arm.ElbowUtils.NeoElbow;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;


public class NeoElbowConstants {

    public static final int NEO_MOTOR_ID = 1;

    public static final double kS = 1;

    public static final double kV = 1;

    public static final double kG = 1;

    public static final double kA = 1;

    public static final SimpleMotorFeedforward SIMPLE_MOTOR_FEED_FORWARD = new SimpleMotorFeedforward(kS, kV, kA);

    public static final boolean IS_REVERSE_LIMIT_SWITCH_ENABLE = true;

    public static final boolean IS_FORWARD_LIMIT_SWITCH_ENABLE = true;

    public static final int PID_SLOT = 0;

    public static final PIDObject NEO_PID = new PIDObject().withKp(0.8).withKd(0.3).withKi(0).withMaxPower(1);

    public static final GBSparkMax.SparkMaxConfObject ELBOW_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
            .withPID(NEO_PID)
            .withIdleMode(CANSparkMax.IdleMode.kBrake)
            .withRampRate(RobotConstants.General.RAMP_RATE_VAL)
            .withPositionConversionFactor(ElbowConstants.CONVERSION_FACTOR)
            .withVelocityConversionFactor(ElbowConstants.CONVERSION_FACTOR)
            .withCurrentLimit(ElbowConstants.CURRENT_LIMIT);


}
