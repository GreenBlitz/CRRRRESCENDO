package edu.greenblitz.robotName.shootingStateService;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.shootingCalculations.ShooterAngle;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;

import static edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants.LEGAL_SHOOTING_ZONE;

public class ShootingState {

    public static boolean isRobotPositionFine() {
        Pose2d robotPose = SwerveChassis.getInstance().getRobotPose();
        return LEGAL_SHOOTING_ZONE.isInCircle(robotPose.getTranslation());
    }

    public static Translation2d getRobotTargetTranslation() {
        Pose2d robotPose = SwerveChassis.getInstance().getRobotPose();
        if (LEGAL_SHOOTING_ZONE.isInCircle(robotPose.getTranslation())) {
            return robotPose.getTranslation();
        }
        return LEGAL_SHOOTING_ZONE.getClosestPositionOnBorder(robotPose.getTranslation());
    }

    public static Rotation2d getRobotTargetAngle() {
        return LEGAL_SHOOTING_ZONE.getTargetRobotAngle(SwerveChassis.getInstance().getRobotPose().getTranslation());
    }

    public static Rotation2d getTargetShooterAngle() {
        Pose2d robotPose = isRobotPositionFine() ? SwerveChassis.getInstance().getRobotPose() : getTargetRobotPosition();
        return ShooterAngle.getShooterAngleBasedOnPosition(
                new Translation3d(
                        robotPose.getX(),
                        robotPose.getY(),
                        RobotConstants.General.SHOOTER_HEIGHT_RELATIVE_TO_GROUND
                )
        );
    }

    public static Pose2d getTargetRobotPosition() {
        return new Pose2d(
                getRobotTargetTranslation(),
                getRobotTargetAngle()
        );
    }

    public static boolean isPositionCorrect() {
        return SwerveChassis.getInstance().isAtPose(getTargetRobotPosition());
    }

    public static boolean isAngleCorrect() {
        return Pivot.getInstance().isAtAngle(getTargetShooterAngle().getRadians());
    }

    public static boolean isReadyToShoot() {
        return isAngleCorrect() && isPositionCorrect();
    }
}
