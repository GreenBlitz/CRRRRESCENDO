package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.auto.MoveToPose;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingState;
import edu.greenblitz.robotName.utils.GBCommand;

public class GoToRobotTargetPosition extends GBCommand {
    @Override
    public void initialize() {
        if (ShootingState.isRobotPositionFine()) {
            new RotateToAngle(ShootingState.getRobotTargetAngle());
        } else {
            MoveToPose.getPathCommand(ShootingState.getTargetRobotPosition());
        }
    }
}
