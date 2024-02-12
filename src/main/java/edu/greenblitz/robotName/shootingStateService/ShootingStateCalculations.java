package edu.greenblitz.robotName.shootingStateService;

import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingAngleCalculations;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;

import static edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants.SHOOTING_ZONE;

public class ShootingStateCalculations {

    public static boolean isRobotInShootingPosition() {
        return SHOOTING_ZONE.isInCircle(getRobotPose().getTranslation());
    }

    public static boolean isRobotNearShootingPosition() {
        return SHOOTING_ZONE.isInCircle(getRobotPose().getTranslation());
    }

    private static Pose2d getRobotPose() {
        return SwerveChassis.getInstance().getRobotPose();
    }

    public static Translation2d getRobotTargetTranslation() {
        return SHOOTING_ZONE.getClosestCirclePosition(getRobotPose().getTranslation());
    }

    public static Rotation2d getTargetRobotAngle() {
        double angle = SHOOTING_ZONE.getTargetRobotAngle().getRadians();
        return new Rotation2d(angle);
    }

    public static Rotation2d getTargetShooterAngle() {
        Pose2d robotPose = isRobotInShootingPosition() ? getRobotPose() : getTargetRobotPosition();
        return ShootingAngleCalculations.getShootingAngle(new Translation3d(robotPose.getX(), robotPose.getY(), PivotConstants.ROBOT_RELATIVE_PIVOT_POSITION.getZ()));
    }

    public static Pose2d getTargetRobotPosition() {
        return new Pose2d(getRobotTargetTranslation(), getTargetRobotAngle());
    }

    public static boolean isChassisAtPosition() {
        return SwerveChassis.getInstance().isAtPose(getTargetRobotPosition());
    }

    public static boolean isPivotAtAngle() {
        return Pivot.getInstance().isAtAngle(getTargetShooterAngle());
    }

    public static boolean isReadyToShoot() {
        return isPivotAtAngle() && isChassisAtPosition();
    }
}