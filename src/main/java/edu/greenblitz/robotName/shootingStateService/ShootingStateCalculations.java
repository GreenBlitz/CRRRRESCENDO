package edu.greenblitz.robotName.shootingStateService;

import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingAngleCalculations;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.math.geometry.*;
import org.littletonrobotics.junction.Logger;

public class ShootingStateCalculations {

	public static boolean isRobotInShootingZone(ShootingZone zone) {
		return zone.isInFullCircle(getRobotPose().getTranslation());
	}

	private static Pose2d getRobotPose() {
		return SwerveChassis.getInstance().getRobotPose2d();
	}

	public static Translation2d getRobotTargetTranslation(ShootingZone zone) {
		Translation2d closestPosition = zone.getClosestCirclePosition(getRobotPose().getTranslation());
		Pose2d robotPose = getRobotPose();
		Logger.recordOutput("Shooter/Calculations/ClosetCirclePosition",closestPosition);
		
		if (closestPosition.getDistance(robotPose.getTranslation()) > ChassisConstants.TRANSLATION_TOLERANCE &&
				closestPosition.getDistance(robotPose.getTranslation()) < ChassisConstants.PATHPLANNER_TRANSLATIONAL_TOLERANCE) {
			return zone.getCenterOfShootingZone();
		}
		return closestPosition;
	}

	public static Rotation2d getTargetRobotAngle(ShootingZone zone) {
		double angle = zone.getTargetRobotAngle().getRadians();
		Logger.recordOutput("Shooter/Calculations/Target Robot Angle",new Rotation2d(angle));
		return new Rotation2d(angle);
	}

	public static Rotation2d getTargetShooterAngle(ShootingZone zone) {
		Pose2d robotPose = isRobotInShootingZone(zone) ? getRobotPose() : getTargetRobotPosition(zone);
		
		Logger.recordOutput("Shooter/Calculations/Target Robot Pose",
				robotPose
		);
		
		Rotation2d angle = ShootingAngleCalculations.getShootingAngle(
				new Translation3d(
						robotPose.getX(),
						robotPose.getY(),
						PivotConstants.ROBOT_RELATIVE_PIVOT_POSITION.getZ()
				)
		);
		
		Logger.recordOutput("Shooter/Calculations/Target Shooter Angle",angle);
		return angle;
	}

	public static Pose2d getTargetRobotPosition(ShootingZone zone) {
		Pose2d pose = new Pose2d(getRobotTargetTranslation(zone), getTargetRobotAngle(zone));
		
		Logger.recordOutput("Shooter/Calculations/Border Robot Pose",
				pose
		);
		return pose;
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