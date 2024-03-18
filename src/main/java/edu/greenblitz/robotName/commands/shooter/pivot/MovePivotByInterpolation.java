package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;

public class MovePivotByInterpolation extends MovePivotToAngle {

    public MovePivotByInterpolation() {
        super(ShootingStateCalculations::getTargetShooterAngle);
    }
}
