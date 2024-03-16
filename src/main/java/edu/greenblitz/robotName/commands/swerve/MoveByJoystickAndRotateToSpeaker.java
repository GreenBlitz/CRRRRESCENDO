package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.OI;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.geometry.Rotation2d;

import java.util.function.DoubleSupplier;

import static edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants.MAX_ANGULAR_SPEED;
import static edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants.ROTATION_PID_CONTROLLER;

public class MoveByJoystickAndRotateToSpeaker extends SwerveCommand {

    private double angularSpeedFactor;

    private double linearSpeedFactor;

    private DoubleSupplier angularVelocitySupplier;

    private MoveByJoysticks.DriveMode driveMode;

    private Rotation2d targetAngle;

    private double factor = 1;

    public MoveByJoystickAndRotateToSpeaker(MoveByJoysticks.DriveMode driveMode, DoubleSupplier angularVelocitySupplier) {
        this.driveMode = driveMode;
        this.angularVelocitySupplier = angularVelocitySupplier;
    }

    public MoveByJoystickAndRotateToSpeaker(MoveByJoysticks.DriveMode driveMode) {
        this(
                driveMode,
                () -> OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.RIGHT_X)
        );
    }

    @Override
    public void initialize() {
        switch (driveMode) {
            case SLOW -> {
                linearSpeedFactor = ChassisConstants.DRIVER_LINEAR_SPEED_FACTOR_SLOW;
                angularSpeedFactor = ChassisConstants.DRIVER_ANGULAR_SPEED_FACTOR_SLOW;
            }
            case NORMAL -> {
                linearSpeedFactor = ChassisConstants.DRIVER_LINEAR_SPEED_FACTOR;
                angularSpeedFactor = ChassisConstants.DRIVER_ANGULAR_SPEED_FACTOR;
            }
        }
    }

    @Override
    public void execute() {
        double leftwardSpeed = OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.LEFT_X) * linearSpeedFactor;

        double forwardSpeed = -OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.LEFT_Y) * linearSpeedFactor;

        double angularSpeed = -angularVelocitySupplier.getAsDouble() * angularSpeedFactor;


        targetAngle = ShootingStateCalculations.getTargetRobotAngle();
        ROTATION_PID_CONTROLLER.setSetpoint(targetAngle.getRadians());
        double pidVelocity = ChassisConstants.ROTATION_PID_CONTROLLER.calculate(swerveChassis.getChassisAngle().getRadians());
        double axesSpeed = Math.abs(leftwardSpeed) + Math.abs(forwardSpeed);
        double angularVelocity = pidVelocity * factor / (axesSpeed + factor);
        double angularVelocityWithJoystick = angularVelocity + angularSpeed;
        double checkedAngularVelocity = Math.min(angularVelocityWithJoystick, MAX_ANGULAR_SPEED);

        if (forwardSpeed == 0 && leftwardSpeed == 0 && checkedAngularVelocity == 0) {
            swerveChassis.stop();
            return;
        }

        swerveChassis.moveByChassisSpeeds(
                forwardSpeed,
                leftwardSpeed,
                checkedAngularVelocity,
                swerveChassis.getChassisAngle()
        );
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}
