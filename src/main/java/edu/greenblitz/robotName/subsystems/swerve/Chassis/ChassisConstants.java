package edu.greenblitz.robotName.subsystems.swerve.Chassis;

import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class ChassisConstants {

    static final Pose2d INITIAL_ROBOT_POSITION = new Pose2d(0, 0, new Rotation2d(0));
    public static final double FRONT_LEFT_X_POSITION = 0.3020647;
    public static final double FRONT_LEFT_Y_POSITION = 0.25265;
    public static final Translation2d[] SWERVE_LOCATIONS_IN_SWERVE_KINEMATICS_COORDINATES = new Translation2d[]{
            new Translation2d(FRONT_LEFT_X_POSITION, FRONT_LEFT_Y_POSITION),
            new Translation2d(FRONT_LEFT_X_POSITION, -FRONT_LEFT_Y_POSITION),
            new Translation2d(-FRONT_LEFT_X_POSITION, FRONT_LEFT_Y_POSITION),
            new Translation2d(-FRONT_LEFT_X_POSITION, -FRONT_LEFT_Y_POSITION)
    };

    public static final PIDController ROTATION_PID_CONTROLLER = new PIDController(3,0,0.2);
    static {
        ROTATION_PID_CONTROLLER.enableContinuousInput(-Math.PI, Math.PI);
    }
    public static final double MAX_VELOCITY = 4.1818320981472068;
    public static final double MAX_ACCELERATION = 14.37979171376739;
    public static final double MAX_ANGULAR_SPEED = 10.454580245368017;
    public static final double FAST_DISCRETION_CONSTANT = 8;
    public static final double SLOW_DISCRETION_CONSTANT = FAST_DISCRETION_CONSTANT / 2;

    public static final double DRIVER_ANGULAR_SPEED_FACTOR = MAX_ANGULAR_SPEED / 2;
    public static final double DRIVER_LINEAR_SPEED_FACTOR = MAX_VELOCITY;

    public static final double DRIVER_ANGULAR_SPEED_FACTOR_SLOW = MAX_ANGULAR_SPEED / 4;
    public static final double DRIVER_LINEAR_SPEED_FACTOR_SLOW = 0.5;

    public static final boolean ANGULAR_JOYSTICK_INVERTED = true;

    public static final boolean LINEAR_JOYSTICK_INVERTED = false;
    public static final MoveByJoysticks.DriveMode DRIVE_MODE = MoveByJoysticks.DriveMode.NORMAL;
  
    public static final boolean FORWARD_VALUE_INVERTED = false;
    public static final boolean LEFTWARD_VALUE_INVERTED = true;

}
