package edu.greenblitz.robotName.commands.swerve.moveTo;

import edu.greenblitz.robotName.commands.swerve.moveTo.MoveToPosition;
import edu.greenblitz.robotName.commands.swerve.rotateTo.RotateToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.utils.shootingCalculations.ShootingZone;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class MoveRobotToShootingPosition extends ConditionalCommand {

    public MoveRobotToShootingPosition(ShootingZone zone) {
        super(
                new RotateToAngle( () -> ShootingStateCalculations.getTargetRobotAngle(zone.getWrapperZone())),
                new MoveToPosition( () -> ShootingStateCalculations.getTargetRobotPosition(zone)),
                () -> ShootingStateCalculations.isRobotInShootingZone(zone.getWrapperZone())
        );
    }
}