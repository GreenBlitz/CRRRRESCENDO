package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;

public class ShootAutomatically extends GoToShootingStateAndShoot{
    public ShootAutomatically(){
        super(ShootingPositionConstants.OPTIMAL_SHOOTING_ZONE);
    }
}
