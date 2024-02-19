package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.PushNoteToFlyWheel;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class GoToShootingStateAndShoot extends SequentialCommandGroup {

    public GoToShootingStateAndShoot(ShootingZone zone) {
        super(
                new GoToShootingState(zone),
                new PushNoteToFlyWheel()
        );
    }

}