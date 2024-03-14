package edu.greenblitz.robotName.shootingStateService;

import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.AllianceUtilities;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingAngleCalculations;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.FieldConstants.MIDDLE_OF_BLUE_SPEAKER_POSITION;
import static edu.greenblitz.robotName.FieldConstants.MIDDLE_OF_RED_SPEAKER_POSITION;

public class ShootingStateCalculations {

	public static Rotation2d getTargetRobotAngle() {
		double xoOffsetY = 0.25;
		Translation2d robotRelative = SwerveChassis.getInstance().getRobotPose2d().getTranslation();
		Translation2d speakerPosition = AllianceUtilities.isBlueAlliance()
				? MIDDLE_OF_BLUE_SPEAKER_POSITION.toTranslation2d()
				: MIDDLE_OF_RED_SPEAKER_POSITION.toTranslation2d();
		Rotation2d angle = Rotation2d.fromRadians(Math.atan2
				(
						speakerPosition.getY() - robotRelative.getY(),
						speakerPosition.getX() - robotRelative.getX()
				)
		);
		angle = angle.plus(Rotation2d.fromDegrees(180));

		Logger.recordOutput("distance to middle of speaker", robotRelative.getDistance(speakerPosition));
		Logger.recordOutput("angle of chassis target", angle.getDegrees());

		return angle;
	}

	public static boolean isRobotInShootingZone(ShootingZone zone) {
		return zone.isInCircle(getRobotPose().getTranslation());
	}

	private static Pose2d getRobotPose() {
		return SwerveChassis.getInstance().getRobotPose2d();
	}

	public static Translation2d getRobotTargetTranslation(ShootingZone zone) {
		Translation2d closestPosition = zone.getClosestCirclePosition(getRobotPose().getTranslation());
		if (closestPosition.getDistance(getRobotPose().getTranslation()) > ChassisConstants.TRANSLATION_TOLERANCE &&
				closestPosition.getDistance(getRobotPose().getTranslation()) < ChassisConstants.PATHPLANNER_TRANSLATIONAL_TOLERANCE) {
			return zone.getCenterOfShootingZone();
		}
		return closestPosition;
	}

	public static Rotation2d getTargetRobotAngle(ShootingZone zone) {
		double angle = zone.getTargetRobotAngle().getRadians();
		return new Rotation2d(angle);
	}

	public static Rotation2d getTargetShooterAngle(ShootingZone zone) {
		Pose2d robotPose = getRobotPose();
//		Pose2d robotPose = isRobotInShootingZone(zone) ? getRobotPose() : getTargetRobotPosition(zone);
		return ShootingAngleCalculations.getShootingAngle(
				new Translation3d(
						robotPose.getX(),
						robotPose.getY(),
						FieldConstants.MIDDLE_OF_SPEAKER_POSITION.getZ()
				)
		);
	}

	public static Pose2d getTargetRobotPosition(ShootingZone zone) {
		return new Pose2d(getRobotTargetTranslation(zone), getTargetRobotAngle(zone));
	}

	public static boolean isChassisAtPosition(ShootingZone zone) {
		return SwerveChassis.getInstance().isAtPose(getTargetRobotPosition(zone));
	}

	public static boolean isPivotAtAngle(ShootingZone zone) {
		return Pivot.getInstance().isAtAngle(getTargetShooterAngle(zone));
	}

	public static boolean isReadyToShoot(ShootingZone zone) {
		return isPivotAtAngle(zone) && isChassisAtPosition(zone);
	}
}