package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.swerve.MoveToPosition;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.utils.GBConditionalProxyCommand;

public class MoveRobotToShootingPosition extends GBConditionalProxyCommand {

    public MoveRobotToShootingPosition() {
        super(
                new RotateToAngle(ShootingStateCalculations::getTargetRobotAngle),
                new MoveToPosition(ShootingStateCalculations::getTargetRobotPosition),
                () -> ShootingStateCalculations.isRobotInShootingPosition()
        );
    }
}