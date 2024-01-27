package edu.greenblitz.robotName.shootingStateService;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingAngle;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;

import static edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants.LEGAL_SHOOTING_ZONE;

public class ShootingState {

	public static boolean isRobotInShootingPosition() {
		return LEGAL_SHOOTING_ZONE.isInCircle(getRobotPose().getTranslation());
	}

	public static Pose2d getRobotPose() {
		return SwerveChassis.getInstance().getRobotPose();
	}

	public static Translation2d getRobotTargetTranslation() {
		if (isRobotInShootingPosition()) {
			return getRobotPose().getTranslation();
		}
		return LEGAL_SHOOTING_ZONE.getClosestPositionOnCircleBorder(getRobotPose().getTranslation());
	}

	public static Rotation2d getRobotTargetAngle() {
		return LEGAL_SHOOTING_ZONE.getTargetRobotAngle(SwerveChassis.getInstance().getRobotPose().getTranslation());
	}

	public static Rotation2d getTargetShooterAngle() {
		Pose2d robotPose = isRobotInShootingPosition() ? getRobotPose() : getTargetRobotPosition();
		return ShootingAngle.getShootingAngleBasedOnPosition(
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