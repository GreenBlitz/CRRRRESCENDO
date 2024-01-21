package edu.greenblitz.robotName.subsystems.Arm.EndEffector;

import com.ctre.phoenix6.configs.ClosedLoopRampsConfigs;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.util.Units;

public class EndEffectorConstants {

    public static final double LENGTH_OF_SHOOTER = 0.3;

    public static final double SHOOTER_MASS_KG = 10;

    public static final double BACKWARD_ANGLE_LIMIT = Units.degreesToRadians(190);

    public static final double FORWARD_ANGLE_LIMIT = Units.degreesToRadians(260);

    public static final double RELATIVE_POSITION_CONVERSION_FACTOR = 0.0328;

    public static final double RELATIVE_VELOCITY_CONVERSION_FACTOR = 0.0030332432;

    public static final double ABSOLUTE_POSITION_CONVERSION_FACTOR = 0.00302;

    public static final double ABSOLUTE_VELOCITY_CONVERSION_FACTOR = 0.00302;

    public static final double STARTING_ANGLE = 1;

    public static final double kS = 1;

    public static final double kV = 1;

    public static final double kG = 1;

    public static final double kA = 1;

    public static final double TOLERANCE = Units.degreesToRadians(2);

    public static final int CURRENT_LIMIT = 40;


    public static class Simulation {

        public static final int NUMBER_OF_MOTORS = 1;

        public static final double GEAR_RATIO = 1 / RELATIVE_POSITION_CONVERSION_FACTOR;

        public static final PIDObject SIM_PID = new PIDObject().withKp(0.8).withKd(0.3).withMaxPower(1);

        public static final double X_POSITION = 1.5;

        public static final double Y_POSITION = 0.5;

    }


    public static class NeoConfigs {

        public static final int MOTOR_ID = 1;

        private static final PIDObject PID = new PIDObject().withKp(0.8).withKd(0.3).withKi(0).withMaxPower(1);

        public static final GBSparkMax.SparkMaxConfObject ELBOW_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
                .withPID(RobotConstants.ROBOT_TYPE != Robot.RobotType.SIMULATION ? PID : Simulation.SIM_PID)
                .withIdleMode(CANSparkMax.IdleMode.kBrake)
                .withRampRate(RobotConstants.General.RAMP_RATE_VAL)
                .withCurrentLimit(CURRENT_LIMIT);
    }
}
