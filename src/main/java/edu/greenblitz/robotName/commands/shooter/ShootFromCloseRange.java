package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.shootingState.GoToShootingStateAndShoot;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;

public class ShootFromCloseRange extends GoToShootingStateAndShoot {

    public ShootFromCloseRange() {
        super(
                ShootingPositionConstants.CLOSE_SHOOTING_ZONE
        );
    }
}