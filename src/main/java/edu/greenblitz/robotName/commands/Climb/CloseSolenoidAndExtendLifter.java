package edu.greenblitz.robotName.commands.Climb;
import edu.greenblitz.robotName.commands.Climb.LifterSolenoid.CloseThenHoldSolenoid;
import edu.greenblitz.robotName.commands.Climb.LifterSolenoid.OpenSolenoid;
import edu.greenblitz.robotName.commands.Climb.lifter.ExtendLifter;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class CloseSolenoidAndExtendLifter extends ParallelCommandGroup {

	public CloseSolenoidAndExtendLifter(){
		super(
				new CloseThenHoldSolenoid(),
				new WaitCommand(1).andThen(new ExtendLifter())
		);
	}
}
