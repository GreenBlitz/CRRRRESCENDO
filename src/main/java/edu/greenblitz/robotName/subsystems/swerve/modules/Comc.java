package edu.greenblitz.robotName.subsystems.swerve.modules;

import edu.greenblitz.robotName.commands.swerve.SwerveCommand;

public class Comc extends SwerveCommand {

	@Override
	public void execute() {
		swerveChassis.setMaxPower();
	}

	@Override
	public void end(boolean interrupted) {
		swerveChassis.stop();
	}
}
