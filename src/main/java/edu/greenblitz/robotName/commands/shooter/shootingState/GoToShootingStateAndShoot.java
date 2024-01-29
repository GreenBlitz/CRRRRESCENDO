package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.Shoot;
import edu.greenblitz.robotName.shootingStateService.ShootingState;
import edu.greenblitz.robotName.utils.GBCommand;

public class GoToShootingStateAndShoot extends GBCommand {
    @Override
    public void initialize() {
        new GoToShootingState();
    }

    @Override
    public void end(boolean interrupted) {
        if(ShootingState.isRobotInShootingPosition()) {
            new Shoot();
        }
    }
}
