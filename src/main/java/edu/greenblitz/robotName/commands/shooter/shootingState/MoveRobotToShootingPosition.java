package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.auto.MoveToPose;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingState;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class MoveRobotToShootingPosition extends ConditionalCommand {

	public MoveRobotToShootingPosition() {
		super(
				new RotateToAngle(ShootingState.getRobotTargetAngle()),
				MoveToPose.getPathCommand(ShootingState.getTargetRobotPosition()),
				ShootingState::isRobotInShootingPosition
		);
	}
}
