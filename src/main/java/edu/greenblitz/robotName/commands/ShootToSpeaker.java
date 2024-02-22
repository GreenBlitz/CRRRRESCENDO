package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.shooter.ShootFromInFunnel;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityConstant;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ShootToSpeaker extends SequentialCommandGroup {

	public ShootToSpeaker() {
		super(
				new RunFlyWheelByVelocityConstant(),
				new ShootFromInFunnel()
		);
	}
}
