package edu.greenblitz.robotName.subsystems.Intake;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class GripperConstants {

    public static double DEFAULT_GRIPPER_POWER = 1;
    public static double VELOCITY_CONVERSION_FACTOR = 1;

    public static class simulationGripper {
        public static final int NUM_MOTORS = 1;
        public static final double GEARING = 187;
        public static final double J_KG_METERS_SQUARED = 187;
    }

    public static class neoGripper {
        public static final int MOTOR_ID = 1;
    }

    public static final GBSparkMax.SparkMaxConfObject GRIPPER_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
            .withVelocityConversionFactor(VELOCITY_CONVERSION_FACTOR)
            .withIdleMode(CANSparkMax.IdleMode.kBrake)
            .withRampRate(RobotConstants.General.RAMP_RATE_VAL)
            .withCurrentLimit(30)
            .withInverted(false);
}
