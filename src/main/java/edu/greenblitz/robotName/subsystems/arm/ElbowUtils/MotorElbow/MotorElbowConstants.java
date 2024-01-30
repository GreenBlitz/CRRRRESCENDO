package edu.greenblitz.robotName.subsystems.arm.ElbowUtils.MotorElbow;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import static edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants.*;

public class MotorElbowConstants {

    public static final int MOTOR_ID = 1;

    public static final SimpleMotorFeedforward SIMPLE_MOTOR_FEED_FORWARD = new SimpleMotorFeedforward(kS, kV, kA);

}
