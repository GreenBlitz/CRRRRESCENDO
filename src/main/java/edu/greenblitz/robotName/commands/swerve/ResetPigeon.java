package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;

public class ResetPigeon extends SwerveCommand{
	
	@Override
	public void initialize() {
		SwerveChassis.getInstance().resetChassisPose();
	}
}
