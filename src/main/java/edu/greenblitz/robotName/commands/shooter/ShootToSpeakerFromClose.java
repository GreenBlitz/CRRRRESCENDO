package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityConstant;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ShootToSpeakerFromClose extends SequentialCommandGroup {

	public ShootToSpeakerFromClose() {
		super(
				new ParallelCommandGroup(
						new MovePivotToAngle(PivotConstants.PresetPositions.CLOSE_SHOOTING.ANGLE)
								.raceWith(new WaitCommand(1.5)),
						new RunFlyWheelByVelocityConstant()
				),
				new PushNoteToFlyWheel()
		);
	}
}
