package edu.greenblitz.robotName.subsystems.Lifter.NeoLifter;

import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxLimitSwitch;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class NeoLifterConstants {
    public static final int MOTOR_ID = 50;
    public static final GBSparkMax.SparkMaxConfObject CONFIG = new GBSparkMax.SparkMaxConfObject()
            .withPID(new PIDObject(3.6, 1, 0));
    public static final double TOLERANCE = 0.04;
    public static final double ENCODER_POSE_WHEN_RESET = 0;
    public static final int BACKWARD_SWITCH_ID = 1;
    public static final int FORWARD_SWITCH_ID = 2;
    public static final double LIFTER_EXTENDED_POSITION = 5;
    public static final double LIFTER_RETRACTED_POSITION = 0;
    
    public static final SimpleMotorFeedforward FEED_FORWARD = new SimpleMotorFeedforward(0,0,0);
    
    public static final SparkMaxLimitSwitch.Type FORWARD_LIMIT_SWITCH_TYPE = SparkMaxLimitSwitch.Type.kNormallyClosed;
    public static final SparkMaxLimitSwitch.Type BACKWARD_LIMIT_SWITCH_TYPE = SparkMaxLimitSwitch.Type.kNormallyClosed;
}
