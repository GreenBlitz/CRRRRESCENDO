package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.Shoot;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class GoToShootingStateAndShoot extends SequentialCommandGroup {

    public GoToShootingStateAndShoot() {
        super(
                new GoToShootingState(),
                new Shoot()
        );
    }
}
