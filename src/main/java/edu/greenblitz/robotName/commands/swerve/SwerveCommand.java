package edu.greenblitz.robotName.commands.swerve;


import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBCommand;

public abstract class SwerveCommand extends GBCommand {
	protected SwerveChassis swerveChassis;
	
	public SwerveCommand() {
		swerveChassis = SwerveChassis.getInstance();
		require(swerveChassis);
	}
}
