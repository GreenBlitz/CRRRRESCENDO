package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class MoveRobotToShootingPosition extends ConditionalCommand {

    public MoveRobotToShootingPosition() {
        super(
                new RotateToAngle(ShootingStateCalculations::getTargetRobotAngle),
                new MoveToPosition(ShootingStateCalculations::getTargetRobotPosition),
                () -> ShootingStateCalculations.isRobotNearShootingPosition()
        );
    }
}