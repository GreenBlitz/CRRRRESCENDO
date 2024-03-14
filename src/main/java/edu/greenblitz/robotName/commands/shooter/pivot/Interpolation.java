package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;

public class Interpolation extends MovePivotToAngle {

    public Interpolation() {
        super(() ->
                ShootingStateCalculations.getTargetShooterAngle(
                        ShootingPositionConstants.LEGAL_SHOOTING_ZONE
                )
        );
    }
}
