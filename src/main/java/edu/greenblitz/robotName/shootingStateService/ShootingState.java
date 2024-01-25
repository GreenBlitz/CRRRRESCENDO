package edu.greenblitz.robotName.shootingStateService;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.shootingCalculations.ShooterAngle;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;

import static edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants.LEGAL_SHOOTING_ZONE;

public class ShootingState {

    public static Translation2d getRobotTargetPosition() {
        Pose2d robotPose = SwerveChassis.getInstance().getRobotPose();
        if (LEGAL_SHOOTING_ZONE.isInCircle(robotPose.getTranslation())) {
            return robotPose.getTranslation();
        }
        return LEGAL_SHOOTING_ZONE.getClosestPositionOnBorder(robotPose.getTranslation());
    }

    public static Rotation2d getShooterTargetAngle() {
        Pose2d robotPose = SwerveChassis.getInstance().getRobotPose();
        return ShooterAngle.getShooterAngleBasedOnPosition(
                new Translation3d(
                        robotPose.getX(),
                        robotPose.getY(),
                        RobotConstants.General.SHOOTER_HEIGHT_RELATIVE_TO_GROUND
                )
        );
    }

    public static boolean isRobotPositionFine() {
        Pose2d robotPose = SwerveChassis.getInstance().getRobotPose();
        return LEGAL_SHOOTING_ZONE.isInCircle(robotPose.getTranslation());
    }
}
