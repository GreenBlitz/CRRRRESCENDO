package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.NeoFlyWheel;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class NeoFlyWheelConstants {

    public static class RightMotor {

        public static final int MOTOR_ID = 44;

        public static final SimpleMotorFeedforward FEEDFORWARD = new SimpleMotorFeedforward(0.5, 0.0022425, 0.00019209);

        public static final double GEAR_RATIO = 8.0;

        public static final int PID_SLOT = 0;

        public static GBSparkMax.SparkMaxConfObject CONFIG = new GBSparkMax.SparkMaxConfObject()
                .withPID(new PIDObject(1, 3, 0))
                .withIdleMode(CANSparkMax.IdleMode.kCoast)
                .withInverted(true);
    }

    public static class LeftMotor {

        public static final int MOTOR_ID = 16;

        public static final SimpleMotorFeedforward FEEDFORWARD = new SimpleMotorFeedforward(0.5, 0.0022425, 0.00019209);

        public static final double GEAR_RATIO = 8.0;

        public static final int PID_SLOT = 0;

        public static GBSparkMax.SparkMaxConfObject CONFIG = new GBSparkMax.SparkMaxConfObject()
                .withPID(new PIDObject(100, 5, 0))
                .withIdleMode(CANSparkMax.IdleMode.kCoast)
                .withInverted(false);
    }
}
