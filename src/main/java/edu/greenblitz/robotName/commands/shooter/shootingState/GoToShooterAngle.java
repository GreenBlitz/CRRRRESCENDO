package edu.greenblitz.robotName.commands.shooter.shootingState;


import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;

public class GoToShooterAngle extends MovePivotToAngle {

    public GoToShooterAngle() {
        super(ShootingStateCalculations.getTargetShooterAngle());
    }
}
