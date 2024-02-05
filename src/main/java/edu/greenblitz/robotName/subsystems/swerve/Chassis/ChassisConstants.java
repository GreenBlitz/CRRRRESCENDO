package edu.greenblitz.robotName.subsystems.swerve.Chassis;


import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class ChassisConstants {

    public static final double ROBOT_WIDTH_IN_METERS = 0.79;
    public static final double BUMPER_WIDTH = 0.13;
    public static final double ROBOT_WIDTH_WITH_BUMPER = ROBOT_WIDTH_IN_METERS + BUMPER_WIDTH;
    public static final double ROBOT_RADIUS = Math.sqrt(Math.pow(ROBOT_WIDTH_WITH_BUMPER, 2) * 2);
    static final Pose2d INITIAL_ROBOT_POSITION = new Pose2d(0, 0, new Rotation2d(0));
    public static final double FRONT_LEFT_X_POSITION = 0.3020647;
    public static final double FRONT_LEFT_Y_POSITION = 0.25265;
    public static final Translation2d[] SWERVE_LOCATIONS_IN_SWERVE_KINEMATICS_COORDINATES = new Translation2d[]{
            new Translation2d(FRONT_LEFT_X_POSITION, FRONT_LEFT_Y_POSITION),
            new Translation2d(FRONT_LEFT_X_POSITION, -FRONT_LEFT_Y_POSITION),
            new Translation2d(-FRONT_LEFT_X_POSITION, FRONT_LEFT_Y_POSITION),
            new Translation2d(-FRONT_LEFT_X_POSITION, -FRONT_LEFT_Y_POSITION)
    };

    public static final PIDController ROTATION_PID_CONTROLLER = new PIDController(3, 0, 0.2);

    static {
        ROTATION_PID_CONTROLLER.enableContinuousInput(-Math.PI, Math.PI);
    }

    public static final double MAX_VELOCITY = 4.1818320981472068;
    public static final double MAX_ACCELERATION = 2;
    public static final double MAX_ANGULAR_SPEED = 10.454580245368017;
    public static final double MAX_ANGULAR_ACCELERATION = 4;
    public static final double FAST_DISCRETION_CONSTANT = 8;
    public static final double SLOW_DISCRETION_CONSTANT = FAST_DISCRETION_CONSTANT / 2;

    public static final double DRIVER_ANGULAR_SPEED_FACTOR = MAX_ANGULAR_SPEED / 2;
    public static final double DRIVER_LINEAR_SPEED_FACTOR = MAX_VELOCITY;

    public static final double DRIVER_ANGULAR_SPEED_FACTOR_SLOW = MAX_ANGULAR_SPEED / 4;
    public static final double DRIVER_LINEAR_SPEED_FACTOR_SLOW = 0.5;

    public static final boolean ANGULAR_JOYSTICK_INVERTED = true;

    public static final boolean LINEAR_JOYSTICK_INVERTED = false;
    public static final PIDConstants TRANSLATION_PID = new PIDConstants(2, 0, 0);
    public static final PIDConstants ROTATION_PID = new PIDConstants(3.6, 0, 0);
    public static final double TOTAL_ERROR_FOR_REPLANNING = 0.5;
    public static final double ERROR_SPIKE_FOR_REPLANNING = 1;
    public static final HolonomicPathFollowerConfig PATH_FOLLOWER_CONFIG = new HolonomicPathFollowerConfig(
            TRANSLATION_PID,
            ROTATION_PID,
            MAX_VELOCITY,
            ROBOT_RADIUS,
            new ReplanningConfig(
                    true,
                    true,
                    TOTAL_ERROR_FOR_REPLANNING,
                    ERROR_SPIKE_FOR_REPLANNING
            )
    );
    public static final PathConstraints CONSTRAINTS = new PathConstraints(
            MAX_VELOCITY,
            MAX_ACCELERATION,
            MAX_ANGULAR_SPEED,
            MAX_ANGULAR_ACCELERATION
    );
    public enum ChassisAnglesAutoShoot{
        //to be fulfilled
        SPEAKER_MIDDLE(Rotation2d.fromDegrees(0)),
        SPEAKER_RIGHT(Rotation2d.fromDegrees(60)),
        SPEAKER_LEFT(Rotation2d.fromDegrees(-60)),
        PODIUM(Rotation2d.fromDegrees(28)),
        AMP_EDGE(Rotation2d.fromDegrees(-36));
        public final Rotation2d ANGLE;
        ChassisAnglesAutoShoot(Rotation2d angle){
            this.ANGLE = angle;
        }
    }
    public static final MoveByJoysticks.DriveMode DRIVE_MODE = MoveByJoysticks.DriveMode.NORMAL;

    public static final boolean FORWARD_VALUE_INVERTED = false;
    public static final boolean LEFTWARD_VALUE_INVERTED = true;

}
