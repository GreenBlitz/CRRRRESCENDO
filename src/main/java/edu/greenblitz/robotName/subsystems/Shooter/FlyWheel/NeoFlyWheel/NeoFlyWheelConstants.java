package edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.NeoFlyWheel;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class NeoFlyWheelConstants {

	public static class RightMotor{
		public static final int MOTOR_ID = 44;
		public static final SimpleMotorFeedforward FEEDFORWARD = new SimpleMotorFeedforward(0.031154, 0.00022425, 0.00019209);
		public static final int PID_SLOT = 0;
		public static GBSparkMax.SparkMaxConfObject CONFIG = new GBSparkMax.SparkMaxConfObject()
				.withPID(new PIDObject(10,0.5,0))
				.withIdleMode(CANSparkMax.IdleMode.kCoast)
				.withInverted(true);
	}

	public static class LeftMotor{
		public static final int MOTOR_ID = 22;
		public static final SimpleMotorFeedforward FEEDFORWARD = new SimpleMotorFeedforward(0.031154, 0.00022425, 0.00019209);
		public static final int PID_SLOT = 0;
		public static GBSparkMax.SparkMaxConfObject CONFIG = new GBSparkMax.SparkMaxConfObject()
				.withPID(new PIDObject(10,0.5,0))
				.withIdleMode(CANSparkMax.IdleMode.kCoast)
				.withInverted(false);

	}

}
