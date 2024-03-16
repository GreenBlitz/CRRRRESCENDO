package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.OI;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

import java.util.function.DoubleSupplier;

public class MoveByJoysticks extends SwerveCommand {
	
	public enum DriveMode {
		SLOW,
		NORMAL
	}
	
	private double angularSpeedFactor;
	
	private double linearSpeedFactor;
	
	private DoubleSupplier angularVelocitySupplier;
	
	private DriveMode driveMode;
	
	public MoveByJoysticks(DriveMode driveMode, DoubleSupplier angularVelocitySupplier) {
		this.driveMode = driveMode;
		this.angularVelocitySupplier = angularVelocitySupplier;
	}
	
	public MoveByJoysticks(DriveMode driveMode) {
		this(
				driveMode,
				() -> OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.RIGHT_X)
		);
	}
	
	@Override
	public void initialize() {
		switch (driveMode) {
			case SLOW:
				linearSpeedFactor = ChassisConstants.DRIVER_LINEAR_SPEED_FACTOR_SLOW;
				angularSpeedFactor = ChassisConstants.DRIVER_ANGULAR_SPEED_FACTOR_SLOW;
				break;
			case NORMAL:
				linearSpeedFactor = ChassisConstants.DRIVER_LINEAR_SPEED_FACTOR;
				angularSpeedFactor = ChassisConstants.DRIVER_ANGULAR_SPEED_FACTOR;
				break;
		}
	}
	
	@Override
	public void execute() {
		//todo - ask Noam
//        double leftwardSpeed = SwerveChassisUtils.joystickValueToOutputValue(
//                OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.LEFT_X),
//                linearSpeedFactor,
//                ChassisConstants.IS_JOYSTICK_LEFTWARD_VALUE_INVERTED
//        );
//        double forwardSpeed = SwerveChassisUtils.joystickValueToOutputValue(
//                OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.LEFT_Y),
//                linearSpeedFactor,
//                ChassisConstants.IS_JOYSTICK_FORWARD_VALUE_INVERTED
//
//        );
//        double angularSpeed = SwerveChassisUtils.joystickValueToOutputValue(
//                angularVelocitySupplier.getAsDouble(),
//                angularSpeedFactor,
//                ChassisConstants.ANGULAR_JOYSTICK_INVERTED
//        );
		double leftwardSpeed = OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.LEFT_X) * linearSpeedFactor;
		
		double forwardSpeed = OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.LEFT_Y) * linearSpeedFactor;
		
		double angularSpeed = angularVelocitySupplier.getAsDouble() * angularSpeedFactor;
		
		if (forwardSpeed == 0 && leftwardSpeed == 0 && angularSpeed == 0) {
			swerveChassis.stop();
			return;
		}

		swerveChassis.moveByChassisSpeeds(
				-forwardSpeed,
				leftwardSpeed,
				-angularSpeed,
				swerveChassis.getChassisAngle()
		);
	}
	
	@Override
	public void end(boolean interrupted) {
		super.end(interrupted);
	}
}