package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;

public class GoToAndShootToSpeaker extends GoToShootingStateAndShoot{
    
    public GoToAndShootToSpeaker(){
        super(ShootingPositionConstants.OPTIMAL_SHOOTING_ZONE);
    }
}
