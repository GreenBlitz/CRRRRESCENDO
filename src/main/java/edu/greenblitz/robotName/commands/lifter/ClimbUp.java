package edu.greenblitz.robotName.commands.lifter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ClimbUp extends SequentialCommandGroup {
	public ClimbUp(){
		super(
				new SolenoidClose(),
				new ExtendLifter()
		);
	}
}
