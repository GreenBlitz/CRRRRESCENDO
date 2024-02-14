package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class MoveShooterToShootingAngle extends ConditionalCommand {

    public MoveShooterToShootingAngle(ShootingZone zone) {
        super(
                new MoveShooterToAngle(() -> ShootingStateCalculations.getTargetShooterAngle(zone.getWrapperZone())),
                new MoveShooterToAngle(() -> ShootingStateCalculations.getTargetShooterAngle(zone)),
                () -> ShootingStateCalculations.isRobotInShootingZone(zone.getWrapperZone())
        );
    }
}
