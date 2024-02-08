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
        super(position, radius);
        restrictedBounds = new ArrayList<>();
    }

    public ShootingZone(Translation2d position, double radius, List<Bound> restrictedBounds) {
        super(position, radius);
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
            Translation2d upperLimit = bound.getUpperLimit();
            Translation2d lowerLimit = bound.getLowerLimit();
            if (bound.isPositionInBound(targetPosition)) {
                return bound.getClosestLimitToPosition(targetPosition);
            }
        }
        return targetPosition;
    }

    public Rotation2d getTargetRobotAngle(Translation2d position) {
        Translation2d relativePosition = ShootingStateCalculations.getRobotTargetTranslation().minus(position);
        Rotation2d angle = new Rotation2d(Math.atan2(relativePosition.getY(), relativePosition.getX()));
        if (angle.getRadians() < 0) {
            angle = Rotation2d.fromRadians(angle.getRadians()+Math.PI*2);
        }
        return angle;
    }
}
