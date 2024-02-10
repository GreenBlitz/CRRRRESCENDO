package edu.greenblitz.robotName.commands.Climb.LifterSolenoid;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CloseThenHoldSolenoid extends SequentialCommandGroup {

	public CloseThenHoldSolenoid(){
		super(
				new CloseSolenoid().withTimeout(0.3),
				new HoldSolenoid()
		);
	}
}
