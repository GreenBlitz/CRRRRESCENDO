package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotCommand;
import edu.greenblitz.robotName.shootingStateService.ShootingState;

public class GoToShooterAngle extends PivotCommand {

    @Override
    public void execute() {
        new MovePivotToAngle(ShootingState.getShooterTargetAngle().getDegrees());
    }
}