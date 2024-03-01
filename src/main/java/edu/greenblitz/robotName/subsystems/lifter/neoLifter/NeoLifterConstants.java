package edu.greenblitz.robotName.subsystems.lifter.neoLifter;

import com.revrobotics.SparkLimitSwitch;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class NeoLifterConstants {

    public static final int MOTOR_ID = 4;

    public static final int SOLENOID_ID = 5;

    public static final GBSparkMax.SparkMaxConfObject CONFIG = new GBSparkMax.SparkMaxConfObject().withPID(new PIDObject(3.6, 1, 0));

    public static final int BACKWARD_SWITCH_ID = 1;

    public static final int FORWARD_SWITCH_ID = 2;

    public static final SimpleMotorFeedforward FEED_FORWARD = new SimpleMotorFeedforward(0, 0, 0);

    public static final SparkLimitSwitch.Type FORWARD_LIMIT_SWITCH_TYPE = SparkLimitSwitch.Type.kNormallyOpen;

    public static final SparkLimitSwitch.Type BACKWARD_LIMIT_SWITCH_TYPE = SparkLimitSwitch.Type.kNormallyClosed;

    public static final boolean IS_BACKWARD_LIMIT_SWITCH_ENABLED = true;
    public static final boolean IS_FORWARD_LIMIT_SWITCH_ENABLED = true;

    public static final int PID_SLOT = 0;
}