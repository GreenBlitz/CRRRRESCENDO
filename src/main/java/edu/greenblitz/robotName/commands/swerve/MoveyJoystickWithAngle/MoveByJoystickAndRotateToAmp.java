package edu.greenblitz.robotName.commands.swerve.MoveyJoystickWithAngle;

import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.OI;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.swerve.SwerveCommand;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.geometry.Rotation2d;

import java.util.function.DoubleSupplier;

import static edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants.MAX_ANGULAR_SPEED;
import static edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants.ROTATION_PID_CONTROLLER;

public class MoveByJoystickAndRotateToAmp extends SwerveCommand {

    private double angularSpeedFactor;

    private double linearSpeedFactor;

    private DoubleSupplier angularVelocitySupplier;

    private MoveByJoysticks.DriveMode driveMode;

    public MoveByJoystickAndRotateToAmp(MoveByJoysticks.DriveMode driveMode, DoubleSupplier angularVelocitySupplier) {
        this.driveMode = driveMode;
        this.angularVelocitySupplier = angularVelocitySupplier;
    }

    public MoveByJoystickAndRotateToAmp(MoveByJoysticks.DriveMode driveMode) {
        this(
                driveMode,
                () -> OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.RIGHT_X)
        );
    }

    @Override
    public void initialize() {
        ROTATION_PID_CONTROLLER.setSetpoint(FieldConstants.BLUE_AMP_ANGLE.getRadians());
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


        double checkedAngularVelocity = MoveByJoystickWithAngleService.calculateAngularVelocity(forwardSpeed, leftwardSpeed, angularSpeed);


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
