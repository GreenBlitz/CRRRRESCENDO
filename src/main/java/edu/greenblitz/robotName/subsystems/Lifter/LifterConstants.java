package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class LifterConstants {

    public static final int MOTOR_ID = 1;

    public static final double TOLERANCE = 0.04;
    public static final double ENCODER_POSE_WHEN_RESET = 0;
    public static final int SWITCH_ID = 1;
    public static final double LIFTER_EXTENDED_POSITION = 5;
    public static final double LIFTER_RETRACTED_POSITION = 0;
    public static final double DEBOUNCE_TIME_FOR_SWITCH = 0.1;
    public static final CANSparkMaxLowLevel.MotorType MOTOR_TYPE = CANSparkMaxLowLevel.MotorType.kBrushless;
    public static final ProfiledPIDController PID = new ProfiledPIDController(3.6, 1, 0, new TrapezoidProfile.Constraints(5, 10));

    public static final Translation3d ROBOT_RELATIVE_LIFTER_POSITION = new Translation3d(0,0.24,0.35);

    public static final Rotation3d ROBOT_RELATIVE_LIFTER_ROTATION = new Rotation3d(Math.PI,0,-Math.PI/2);
}
