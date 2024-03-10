package edu.greenblitz.robotName.commands.lifter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CloseAndThenHoldSolenoid extends SequentialCommandGroup {

	public CloseAndThenHoldSolenoid() {
		super(
				new PushSolenoidWheel(),
				new SolenoidClose(),
				new SolenoidHold()
		);
	}
}
