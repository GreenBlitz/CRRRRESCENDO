package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.auto.MoveToPosition;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import org.littletonrobotics.junction.Logger;

public class MoveRobotToShootingPosition extends ConditionalCommand {

    public MoveRobotToShootingPosition() {
        super(
                new RotateToAngle(ShootingStateCalculations::getTargetRobotAngle),
                new MoveToPosition(ShootingStateCalculations::getTargetRobotPosition),
                ShootingStateCalculations::isRobotInShootingPosition
        );
    }
}