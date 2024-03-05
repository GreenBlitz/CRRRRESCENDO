package edu.greenblitz.robotName.commands.lifter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class SolenoidAndLifter extends SequentialCommandGroup {
	public SolenoidAndLifter(){
		super(
				new CloseAndThenHoldSolenoid().andThen(new InstantCommand(
						()-> SmartDashboard.putBoolean("done", true)
				)),
				new MoveLifterByPower(0.1)
		);
	}
}
