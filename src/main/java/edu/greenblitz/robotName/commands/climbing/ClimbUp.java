package edu.greenblitz.robotName.commands.climbing;

import edu.greenblitz.robotName.commands.climbing.lifter.RetractLifter;
import edu.greenblitz.robotName.commands.climbing.lifter.SlowRetractLifter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ClimbUp extends SequentialCommandGroup {

	public ClimbUp(){
		super(
				new RetractLifter(),
				new SlowRetractLifter()
		);
	}

}
