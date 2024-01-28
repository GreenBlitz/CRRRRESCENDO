package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.auto.MoveToPosition;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingState;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;

public class MoveRobotToShootingPosition extends ConditionalCommand {

    public MoveRobotToShootingPosition() {
        super(
                new RotateToAngle(ShootingState.getRobotTargetAngle()),
                new MoveToPosition(ShootingState.getTargetRobotPosition()),
                ShootingState::isRobotInShootingPosition
        );
    }
}
