package edu.greenblitz.robotName.commands.swerve.MoveyJoystickWithAngle;

import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.AllianceUtilities;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants.MAX_ANGULAR_SPEED;

public class MoveByJoystickWithAngleService {
	
	private static final double FACTOR = 1.25;

	public static double getForwardFactor(MoveByJoysticks.DriveMode mode){
		if (!AllianceUtilities.isBlueAlliance()) {
			return mode == MoveByJoysticks.DriveMode.SLOW ? -ChassisConstants.DRIVER_LINEAR_SPEED_FACTOR_SLOW :-ChassisConstants.MAX_VELOCITY;
		}
		else{
			return mode == MoveByJoysticks.DriveMode.SLOW ? ChassisConstants.DRIVER_LINEAR_SPEED_FACTOR_SLOW : ChassisConstants.MAX_VELOCITY;
		}
	}

	public static double calculateAngularVelocity(double forwardSpeed, double leftwardSpeed, double angularSpeed) {
		double pidVelocity = ChassisConstants.ROTATION_PID_CONTROLLER.calculate(SwerveChassis.getInstance().getChassisAngle().getRadians());
		double axesSpeed = Math.sqrt((forwardSpeed * forwardSpeed) + (leftwardSpeed * leftwardSpeed));
		double angularVelocity = pidVelocity * FACTOR / (axesSpeed + FACTOR);
		double angularVelocityWithJoystick = angularVelocity + angularSpeed;
		double checkedAngularVelocity = MathUtil.clamp(angularVelocityWithJoystick, -MAX_ANGULAR_SPEED, MAX_ANGULAR_SPEED);
		return checkedAngularVelocity;
	}
}
