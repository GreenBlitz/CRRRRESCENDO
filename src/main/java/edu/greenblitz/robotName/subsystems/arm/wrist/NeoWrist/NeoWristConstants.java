package edu.greenblitz.robotName.subsystems.arm.wrist.NeoWrist;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import static edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants.CURRENT_LIMIT;

public class NeoWristConstants {

    public static final int MOTOR_ID = 1;

    public static final int PID_SLOT = 0;

    public static final PIDObject NEO_PID = new PIDObject().withKp(0.8).withKd(0.3).withKi(0).withMaxPower(1);

    public static final SimpleMotorFeedforward WRIST_FEED_FORWARD = new SimpleMotorFeedforward(WristConstants.kS, WristConstants.kV, WristConstants.kA);

    public static final GBSparkMax.SparkMaxConfObject WRIST_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
            .withPID(NEO_PID)
            .withIdleMode(CANSparkMax.IdleMode.kBrake)
            .withRampRate(RobotConstants.General.RAMP_RATE_VALUE)
            .withCurrentLimit(CURRENT_LIMIT);


}
