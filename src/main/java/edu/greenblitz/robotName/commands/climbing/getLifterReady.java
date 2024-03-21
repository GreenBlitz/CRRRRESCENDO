package edu.greenblitz.robotName.commands.climbing;

import edu.greenblitz.robotName.commands.climbing.lifter.ExtendLifter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class getLifterReady extends SequentialCommandGroup {

	public getLifterReady() {
		super(
				new CloseAndThenHoldSolenoid(),
				new ExtendLifter()
		);
	}
}
