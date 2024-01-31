package edu.greenblitz.robotName.utils.shootingCalculations;


import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.utils.GBCircle;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

import java.util.ArrayList;
import java.util.List;

public class ShootingZone extends GBCircle {

    private List<Pair<Translation2d, Translation2d>> restrictedBounds; //first is the lower bound, second is the upper

    public ShootingZone(Translation2d position, double radius) {
        super(position, radius);
        restrictedBounds = new ArrayList<>();
    }

    public ShootingZone(Translation2d position, double radius, List<Pair<Translation2d, Translation2d>> restrictedBounds) {
        super(position, radius);
        this.restrictedBounds = restrictedBounds;
    }

    public void addBound(Pair<Translation2d, Translation2d> bound) {

        restrictedBounds.add(bound);
    }

    public void setRestrictedBounds(List<Pair<Translation2d, Translation2d>> restrictedBounds) {
        this.restrictedBounds = restrictedBounds;
    }

    public Translation2d getClosestLimitToPosition(Translation2d upperLimit, Translation2d lowerLimit, Translation2d targetPosition) {
        double upperLimitTargetDelta = upperLimit.getY() - targetPosition.getY();
        double lowerLimitTargetDelta = targetPosition.getY() - lowerLimit.getY();
        return upperLimitTargetDelta > lowerLimitTargetDelta ? lowerLimit : upperLimit;
    }

    public boolean isInXBound(Translation2d position, Translation2d lowerLimit, Translation2d upperLimit) {
        return position.getX() > lowerLimit.getX() && position.getX() < upperLimit.getX();
    }

    public boolean isInYBound(Translation2d position, Translation2d lowerLimit, Translation2d upperLimit) {
        return position.getY() > lowerLimit.getY() && position.getY() < upperLimit.getY();
    }

    public boolean isPositionInBound(Translation2d position, Translation2d lowerLimit, Translation2d upperLimit) {
        boolean isInXBound = lowerLimit.getX() > upperLimit.getX() ?
                isInXBound(position, upperLimit, lowerLimit) :
                isInXBound(position, lowerLimit, upperLimit);
        return isInXBound && isInYBound(position, lowerLimit, upperLimit);
    }

    @Override
    public Translation2d getClosestPositionOnCircleBorder(Translation2d position) {
        Translation2d targetPosition = super.getClosestPositionOnCircleBorder(position);
        for (Pair<Translation2d, Translation2d> bound : restrictedBounds) {
            Translation2d upperLimit = bound.getSecond();
            Translation2d lowerLimit = bound.getFirst();
            if (isPositionInBound(targetPosition, lowerLimit, upperLimit)) {
                return getClosestLimitToPosition(upperLimit, lowerLimit, targetPosition);
            }
        }
        return targetPosition;
    }

    public Rotation2d getTargetRobotAngle(Translation2d position) {
        Translation2d relativePosition = ShootingStateCalculations.getRobotTargetTranslation().minus(position);
        return new Rotation2d(Math.atan2(relativePosition.getY(), relativePosition.getX()));
    }
}
