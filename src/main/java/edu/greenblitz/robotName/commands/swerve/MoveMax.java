package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;

public class MoveMax extends SwerveCommand{

	@Override
	public void execute() {
		SwerveChassis.getInstance().moveByPower();
	}
}
