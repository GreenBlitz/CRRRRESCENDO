package edu.greenblitz.robotName.utils.shootingCalculations;

import edu.greenblitz.robotName.utils.GBMath;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Translation2d;

import java.util.List;

public class ShootingZone extends GBMath.GBCircle {
    private List<Pair<Translation2d, Translation2d>> restrictedBounds; //first min, second max

    public ShootingZone(Translation2d translation, double radius) {
        super(translation, radius);
    }

    public void addBound(Pair<Translation2d, Translation2d> bound) {
        restrictedBounds.add(bound);
    }

    public void setRestrictedBounds(List<Pair<Translation2d, Translation2d>> restrictedBounds) {
        this.restrictedBounds = restrictedBounds;
    }

    @Override
    public Translation2d getClosestPositionOnBorder(Translation2d position) {
        Translation2d targetPosition = super.getClosestPositionOnBorder(position);
        for (Pair<Translation2d, Translation2d> bound : restrictedBounds) {
            Translation2d upperLimit = bound.getSecond();
            Translation2d lowerLimit = bound.getFirst();
            if (targetPosition.getY() > lowerLimit.getY() && targetPosition.getY() < upperLimit.getY()) {
                double upperLimitTargetDelta = upperLimit.getY() - targetPosition.getY();
                double lowerLimitTargetDelta = targetPosition.getY() - lowerLimit.getY();
                return upperLimitTargetDelta > lowerLimitTargetDelta ? lowerLimit : upperLimit;
            }
        }
        return targetPosition;
    }
}
