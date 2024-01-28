package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.auto.MoveToPose;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.commands.swerve.SwerveCommand;
import edu.greenblitz.robotName.shootingStateService.ShootingState;
import edu.greenblitz.robotName.utils.GBCommand;

public class MoveRobotToShootingPosition extends SwerveCommand {
    @Override
    public void initialize() {
        if (ShootingState.isRobotInShootingPosition()) {
            new RotateToAngle(ShootingState.getRobotTargetAngle());
        } else {
            MoveToPose.getPathCommand(ShootingState.getTargetRobotPosition());
        }
    }
}