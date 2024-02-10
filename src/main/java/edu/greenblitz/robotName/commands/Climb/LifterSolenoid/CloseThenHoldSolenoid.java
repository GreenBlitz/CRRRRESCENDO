package edu.greenblitz.robotName.commands.Climb.LifterSolenoid;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CloseThenHoldSolenoid extends SequentialCommandGroup {

	public CloseThenHoldSolenoid(){
		super(
				new CloseSolenoid(),
				new HoldSolenoid()
		);
	}
}
