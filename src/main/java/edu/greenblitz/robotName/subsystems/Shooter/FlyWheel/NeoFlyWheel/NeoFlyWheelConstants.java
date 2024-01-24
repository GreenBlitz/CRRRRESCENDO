package edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.NeoFlyWheel;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class NeoFlyWheelConstants {

	public static class RightMotor{
		public static final int MOTOR_ID = 7;
		public static final SimpleMotorFeedforward FEEDFORWARD = new SimpleMotorFeedforward(1, 0, 0);
		public static final int PID_SLOT = 0;
	}

	public static class LeftMotor{
		public static final int MOTOR_ID = 7;
		public static final SimpleMotorFeedforward FEEDFORWARD = new SimpleMotorFeedforward(1, 0, 0);
		public static final int PID_SLOT = 0;
	}

}
