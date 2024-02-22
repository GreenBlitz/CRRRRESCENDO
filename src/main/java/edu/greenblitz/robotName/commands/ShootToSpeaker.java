package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.shooter.ShootFromInFunnel;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityConstant;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ShootToSpeaker extends SequentialCommandGroup {

	public ShootToSpeaker() {
		super(
				new RunFlyWheelByVelocityConstant().alongWith(new MovePivotToAngle(Rotation2d.fromDegrees(45))),
				new ShootFromInFunnel()
		);
	}
}
