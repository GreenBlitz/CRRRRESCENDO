package edu.greenblitz.robotName.commands.swerve.MoveyJoystickWithAngle;

import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;

import static edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants.MAX_ANGULAR_SPEED;

public class MoveByJoystickWithAngleService {

	private static double FACTOR = 1;

	public static double calculateAngularVelocity(double forwardSpeed, double leftwardSpeed, double angularSpeed) {
		double pidVelocity = ChassisConstants.ROTATION_PID_CONTROLLER.calculate(SwerveChassis.getInstance().getChassisAngle().getRadians());
		double axesSpeed = Math.sqrt((forwardSpeed * forwardSpeed) + (leftwardSpeed * leftwardSpeed));
		double angularVelocity = pidVelocity * FACTOR / (axesSpeed + FACTOR);
		double angularVelocityWithJoystick = angularVelocity + angularSpeed;
		double checkedAngularVelocity = Math.min(angularVelocityWithJoystick, MAX_ANGULAR_SPEED);
		return checkedAngularVelocity;
	}

}
