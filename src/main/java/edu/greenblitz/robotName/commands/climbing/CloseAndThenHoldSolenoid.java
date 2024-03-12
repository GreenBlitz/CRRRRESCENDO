package edu.greenblitz.robotName.commands.climbing;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CloseAndThenHoldSolenoid extends SequentialCommandGroup {

	public CloseAndThenHoldSolenoid() {
		super(
				new PushSolenoidWheel()
		);
	}
}
