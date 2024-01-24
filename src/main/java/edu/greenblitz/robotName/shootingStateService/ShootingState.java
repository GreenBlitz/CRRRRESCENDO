package edu.greenblitz.robotName.shootingStateService;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.greenblitz.robotName.utils.shootingCalculations.ShooterAngle;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;

public class ShootingState extends GBSubsystem {
    private ShootingZone zone;
    private static ShootingState instance;
    private Pose2d robotPose;

    private ShootingState() {
        zone = new ShootingZone(
                ShootingPositionConstants.CENTER_OF_CIRCLE,
                ShootingPositionConstants.CIRCLE_RADIUS_IN_METERS
        );
        robotPose = SwerveChassis.getInstance().getRobotPose();
    }

    public static void init() {
        if (instance == null) {
            instance = new ShootingState();
        }
    }

    public static ShootingState getInstance() {
        init();
        return instance;
    }

    public Translation2d getRobotTargetPosition() {
        if (zone.isInCircle(robotPose.getTranslation())) {
            return robotPose.getTranslation();
        }
        return zone.getClosestPositionOnBorder(robotPose.getTranslation());
    }

    public Rotation2d getShooterTargetAngle() {
        return ShooterAngle.getShooterAngleBasedOnPosition(
                new Translation3d(
                        robotPose.getX(),
                        robotPose.getY(),
                        RobotConstants.General.SHOOTER_HEIGHT_RELATIVE_TO_GROUND
                )
        );
    }

    public boolean isRobotPositionFine() {
        return zone.isInCircle(robotPose.getTranslation());
    }
}
