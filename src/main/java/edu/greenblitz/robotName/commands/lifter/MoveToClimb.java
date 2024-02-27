package edu.greenblitz.robotName.commands.lifter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class MoveToClimb extends SequentialCommandGroup {

	public MoveToClimb() {
		super(
			new ReleaseLifter(),
			new ExtendLifter()
		);
	}
}
