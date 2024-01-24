package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class LifterConstants {
    public static final int MOTOR_ID = 1;
    public static final double PID_KP = 3.6;
    public static final double PID_KI = 1;
    public static final double PID_KD = 0;
    public static final double TOLERANCE = 0.04;
    public static final double MAX_VELOCITY = 5;
    public static final double MAX_ACCELERATION = 10;
    public static final int SWITCH_ID = 1;
    public static final double LIFTER_EXTENDED_POSITION = 5;
    public static final double LIFTER_RETRACTED_POSITION = 0;
    public static final CANSparkMaxLowLevel.MotorType MOTOR_TYPE = CANSparkMaxLowLevel.MotorType.kBrushless;
    public static final ProfiledPIDController PID = new ProfiledPIDController(LifterConstants.PID_KP, LifterConstants.PID_KI, LifterConstants.PID_KD, new TrapezoidProfile.Constraints(LifterConstants.MAX_VELOCITY, LifterConstants.MAX_ACCELERATION));
}
