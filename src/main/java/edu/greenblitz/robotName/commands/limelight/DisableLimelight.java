package edu.greenblitz.robotName.commands.limelight;

import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class DisableLimelight extends InstantCommand {
	
	@Override
	public void initialize() {
		SwerveChassis.getInstance().disableLimelight();
	}
}
