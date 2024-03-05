package edu.greenblitz.robotName.commands.lifter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class SolenoidAndLifter extends SequentialCommandGroup {
	public SolenoidAndLifter(){
		super(
				new CloseAndThenHoldSolenoid(),
				new ExtendLifter()
		);
	}
}
