package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.swerve.MoveToPosition;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class MoveRobotToShootingPosition extends MoveToPosition {

    public MoveRobotToShootingPosition() {
        super(
                ShootingStateCalculations::getTargetRobotPosition
        );
    }
}