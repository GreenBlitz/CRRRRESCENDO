package edu.greenblitz.robotName.commands.climbing.lifter;

import edu.greenblitz.robotName.commands.climbing.CloseAndThenHoldSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class MoveSolenoidAndLifter extends SequentialCommandGroup {

	public MoveSolenoidAndLifter(){
		super(
				new CloseAndThenHoldSolenoid(),
				new InstantCommand(
						()-> SmartDashboard.putBoolean("done", true)
				),
				new MoveLifterByPower(0.1)
		);
	}
}
