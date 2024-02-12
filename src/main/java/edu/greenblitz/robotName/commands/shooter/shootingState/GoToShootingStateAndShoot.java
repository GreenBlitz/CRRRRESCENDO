package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.PushNoteToFlyWheel;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class GoToShootingStateAndShoot extends SequentialCommandGroup {

    public GoToShootingStateAndShoot(ShootingZone bigZone, ShootingZone smallZone) {
        super(
                new GoToShootingState(bigZone,smallZone),
                new PushNoteToFlyWheel()
        );
    }

    public GoToShootingStateAndShoot() {
        this(ShootingPositionConstants.LEGAL_SHOOTING_ZONE,ShootingPositionConstants.OPTIMAL_SHOOTING_ZONE);
    }
}
