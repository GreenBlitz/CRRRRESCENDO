package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class MoveShooterToShootingAngle extends MoveShooterToAngle {

    public MoveShooterToShootingAngle(ShootingZone zone) {
        super(
                () -> ShootingStateCalculations.getTargetShooterAngle(zone)
        );
    }
}
