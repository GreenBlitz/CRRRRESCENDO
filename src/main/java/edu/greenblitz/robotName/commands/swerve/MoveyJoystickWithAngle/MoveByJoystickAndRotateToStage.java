package edu.greenblitz.robotName.commands.swerve.MoveyJoystickWithAngle;

import edu.greenblitz.robotName.Field;
import edu.greenblitz.robotName.OI;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.swerve.SwerveCommand;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.AllianceUtilities;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

import java.util.function.DoubleSupplier;

import static edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants.ROTATION_PID_CONTROLLER;

public class MoveByJoystickAndRotateToStage extends SwerveCommand {

    private double angularSpeedFactor;

    private double linearSpeedFactor;

    private double leftwardSpeedFactor;

    private DoubleSupplier angularVelocitySupplier;

    private MoveByJoysticks.DriveMode driveMode;

    private Rotation2d targetAngle;


    public MoveByJoystickAndRotateToStage(MoveByJoysticks.DriveMode driveMode, DoubleSupplier angularVelocitySupplier) {
        this.driveMode = driveMode;
        this.angularVelocitySupplier = angularVelocitySupplier;
    }

    public MoveByJoystickAndRotateToStage(MoveByJoysticks.DriveMode driveMode) {
        this(
                driveMode,
                () -> OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.RIGHT_X)
        );
    }

    @Override
    public void initialize() {
        switch (driveMode) {
            case SLOW -> {
                linearSpeedFactor = MoveByJoystickWithAngleService.getForwardFactor(MoveByJoysticks.DriveMode.SLOW);
                leftwardSpeedFactor = -linearSpeedFactor;
                angularSpeedFactor = ChassisConstants.DRIVER_ANGULAR_SPEED_FACTOR;
            }
            case NORMAL -> {
                linearSpeedFactor = MoveByJoystickWithAngleService.getForwardFactor(MoveByJoysticks.DriveMode.NORMAL);
                leftwardSpeedFactor = -linearSpeedFactor;
                angularSpeedFactor = ChassisConstants.DRIVER_ANGULAR_SPEED_FACTOR;
            }
        }
    }

    @Override
    public void execute() {
        double leftwardSpeed = OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.LEFT_X) * leftwardSpeedFactor;

        double forwardSpeed = OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.LEFT_Y) * linearSpeedFactor;

        double angularSpeed = -angularVelocitySupplier.getAsDouble() * angularSpeedFactor;


        targetAngle = getClosestClimbPosition();
        ROTATION_PID_CONTROLLER.setSetpoint(targetAngle.getRadians());
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

    public static Rotation2d getClosestClimbPosition() {
        return AllianceUtilities.isBlueAlliance() ? getClosestBlueClimbPosition() : getClosestRedClimbPosition();
    }

    public static Rotation2d getClosestRedClimbPosition() {
        Pose2d robotPose = SwerveChassis.getInstance().getRobotPose2d();

        double distanceToLower = robotPose.getTranslation().getDistance(Field.ScoringPositions.RED_SOURCE_CLIMB_POSITION.getTranslation());
        double distanceToUpper = robotPose.getTranslation().getDistance(Field.ScoringPositions.RED_AMP_CLIMB_POSITION.getTranslation());
        double distanceToMid = robotPose.getTranslation().getDistance(Field.ScoringPositions.RED_MID_CLIMB_POSITION.getTranslation());

        double closestDistance = Math.min(distanceToMid, Math.min(distanceToLower, distanceToUpper));

        if (closestDistance == distanceToLower) {
            return Field.ScoringPositions.RED_SOURCE_CLIMB_POSITION.getRotation();
        }
        if (closestDistance == distanceToUpper) {
            return Field.ScoringPositions.RED_AMP_CLIMB_POSITION.getRotation();

        } else {
            return Field.ScoringPositions.RED_MID_CLIMB_POSITION.getRotation();
        }
    }

    public static Rotation2d getClosestBlueClimbPosition() {
        Pose2d robotPose = SwerveChassis.getInstance().getRobotPose2d();

        double distanceToSource = robotPose.getTranslation().getDistance(Field.ScoringPositions.BLUE_SOURCE_CLIMB_POSITION.getTranslation());
        double distanceToAmp = robotPose.getTranslation().getDistance(Field.ScoringPositions.BLUE_AMP_CLIMB_POSITION.getTranslation());
        double distanceToMid = robotPose.getTranslation().getDistance(Field.ScoringPositions.BLUE_MID_CLIMB_POSITION.getTranslation());

        double closestDistance = Math.min(distanceToMid, Math.min(distanceToSource, distanceToAmp));

        if (closestDistance == distanceToSource) {
            return Field.ScoringPositions.BLUE_SOURCE_CLIMB_POSITION.getRotation();
        }
        if (closestDistance == distanceToAmp) {
            return Field.ScoringPositions.BLUE_AMP_CLIMB_POSITION.getRotation();
        } else {
            return Field.ScoringPositions.BLUE_MID_CLIMB_POSITION.getRotation();
        }
    }
}
