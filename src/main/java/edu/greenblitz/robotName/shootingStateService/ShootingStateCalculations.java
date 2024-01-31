package edu.greenblitz.robotName.shootingStateService;

import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingAngle;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;

import static edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants.LEGAL_SHOOTING_ZONE;

public class ShootingStateCalculations {

    public static boolean isRobotInShootingPosition() {
        return LEGAL_SHOOTING_ZONE.isInCircle(getRobotPose().getTranslation());
    }

    public static Pose2d getRobotPose() {
        return SwerveChassis.getInstance().getRobotPose();
    }

    public static Translation2d getRobotTargetTranslation() {
        return LEGAL_SHOOTING_ZONE.getClosestPositionOnCircleBorder(getRobotPose().getTranslation());
    }

    public static Rotation2d getTargetRobotAngle() {
        double angle = LEGAL_SHOOTING_ZONE.getTargetRobotAngle(SwerveChassis.getInstance().getRobotPose().getTranslation()).getRadians();
        angle -= isRobotInShootingPosition() ? Math.PI : 0;
        return new Rotation2d(angle);
    }

    public static Rotation2d getTargetShooterAngle() {
        Pose2d robotPose = isRobotInShootingPosition() ? getRobotPose() : getTargetRobotPosition();
        return ShootingAngle.getShootingAngleBasedOnPosition(
                new Translation3d(
                        robotPose.getX(),
                        robotPose.getY(),
                        PivotConstants.ROBOT_RELATIVE_PIVOT_POSITION.getZ()
                )
        );
    }

    public static Pose2d getTargetRobotPosition() {
        return new Pose2d(
                getRobotTargetTranslation(),
                getTargetRobotAngle()
        );
    }

    public static boolean isPositionCorrect() {
        return SwerveChassis.getInstance().isAtPose(getTargetRobotPosition());
    }

    public static boolean isAngleCorrect() {
        return Pivot.getInstance().isAtAngle(getTargetShooterAngle());
    }

    public static boolean isReadyToShoot() {
        return isAngleCorrect() && isPositionCorrect();
    }
}