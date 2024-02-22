package edu.greenblitz.robotName.commands.swerve;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ToggleRobotRelative extends InstantCommand {

	@Override
	public void initialize() {
		MoveByJoysticks.isRobotRelative = !MoveByJoysticks.isRobotRelative;
	}
}
