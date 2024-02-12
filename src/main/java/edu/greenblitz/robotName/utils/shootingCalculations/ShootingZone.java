package edu.greenblitz.robotName.utils.shootingCalculations;


import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.utils.GBCircle;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

import java.util.ArrayList;
import java.util.List;

public class ShootingZone extends GBCircle {

    private List<Bound> restrictedBounds;

    public ShootingZone(Translation2d position, double radius) {
        this(position,radius,new ArrayList<>());
    }

    public ShootingZone(Translation2d position, double radius, List<Bound> restrictedBounds) {
        super(position, radius);
        this.restrictedBounds = restrictedBounds;
    }

    public ShootingZone(Translation2d position, double radius, List<Bound> restrictedBounds, Rotation2d lowerAngleLimit, Rotation2d upperAngleLimit) {
        super(position, radius, lowerAngleLimit, upperAngleLimit);
        this.restrictedBounds = restrictedBounds;
    }

    public void addBound(Bound bound) {
        restrictedBounds.add(bound);
    }

    public void setRestrictedBounds(List<Bound> restrictedBounds) {
        this.restrictedBounds = restrictedBounds;
    }

    @Override
    public Translation2d getClosestCircleRimPosition(Translation2d position) {
        Translation2d targetPosition = super.getClosestCircleRimPosition(position);
        for (Bound bound : restrictedBounds) {
            if (bound.isPositionInBound(targetPosition)) {
                return bound.getClosestLimitToPosition(targetPosition);
            }
        }
        return targetPosition;
    }

    @Override
    public Translation2d getClosestCirclePosition(Translation2d position) {
        Translation2d targetPosition = super.getClosestCirclePosition(position);
        for (Bound bound : restrictedBounds) {
            if (bound.isPositionInBound(targetPosition)) {
                return bound.getClosestLimitToPosition(targetPosition);
            }
        }
        return targetPosition;
    }

    public Rotation2d getTargetRobotAngle() {
        Translation2d relativePosition = ShootingStateCalculations.getRobotTargetTranslation().minus(getCenterPosition());
        Rotation2d angle = new Rotation2d(Math.atan2(relativePosition.getY(), relativePosition.getX()));
        angle = angle.minus(Rotation2d.fromRotations(0.5));
        return angle;
    }
}
