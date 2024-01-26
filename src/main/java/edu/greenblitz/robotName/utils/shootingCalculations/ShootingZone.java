package edu.greenblitz.robotName.utils.shootingCalculations;


import edu.greenblitz.robotName.utils.GBMath;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

import java.util.ArrayList;
import java.util.List;

public class ShootingZone extends GBMath.GBCircle {

    private List<Pair<Translation2d, Translation2d>> restrictedBounds; //first min, second max

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

    public Rotation2d getTargetRobotAngle(Translation2d position) {
        Translation2d relativePosition = ShootingState.getRobotTargetTranslation().minus(position);
        return new Rotation2d(Math.atan2(relativePosition.getY(), relativePosition.getX()));
    }
}{
}
