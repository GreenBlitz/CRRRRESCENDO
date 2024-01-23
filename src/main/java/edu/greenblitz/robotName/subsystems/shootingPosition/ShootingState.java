package edu.greenblitz.robotName.subsystems.shootingPosition;

import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBMath;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.math.geometry.Translation2d;

public class ShootingState extends GBSubsystem {
    private ShootingZone zone;
    private ShootingState instance;

    private ShootingState() {
        zone = new ShootingZone(
                ShootingPositionConstants.CENTER_OF_CIRCLE,
                ShootingPositionConstants.CIRCLE_RADIUS_IN_METERS
        );
    }

    public void init() {
        if (instance == null) {
            instance = new ShootingState();
        }
    }

    public ShootingState getInstance() {
        init();
        return instance;
    }

    public Translation2d getRobotTargetPosition() {
        if (zone.isInCircle(SwerveChassis.getInstance().getRobotPose().getTranslation())) {
            return SwerveChassis.getInstance().getRobotPose().getTranslation();
        }
        return zone.getClosestPositionOnBorder(SwerveChassis.getInstance().getRobotPose().getTranslation());
    }

    public double getShooterTargetAngle() {
//        return GBMath.getShooterAngleBasedOnPosition(SwerveChassis.getInstance().getRobotPose().getTranslation());
    }
}
