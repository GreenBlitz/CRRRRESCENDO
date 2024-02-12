package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class MoveRobotToShootingPosition extends ConditionalCommand {

    public MoveRobotToShootingPosition(ShootingZone bigZone, ShootingZone smallZone) {
        super(
                new RotateToAngle( () -> ShootingStateCalculations.getTargetRobotAngle(smallZone)),
                new MoveToPosition( () -> ShootingStateCalculations.getTargetRobotPosition(smallZone)),
                () -> ShootingStateCalculations.isRobotInShootingZone(bigZone)
        );
    }
}