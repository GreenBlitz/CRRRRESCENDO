package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.*;

public class MoveShooterToAngle extends ConditionalCommand {
	public MoveShooterToAngle(Rotation2d targetAngle) {
		super(
				new ParallelCommandGroup(
						new GBCommand() {
							@Override
							public void initialize() {
								new MoveElbowAndWrist(
										ElbowConstants.PresetPositions.SAFE.ANGLE,
										WristConstants.PresetPositions.TRANSFER.ANGLE
								).schedule();
							}
						},
						new SequentialCommandGroup(
								new WaitCommand(PivotConstants.DELAY_FOR_NO_COLLISION_SECONDS),
								new MovePivotToAngle(targetAngle)
						)
				),
				new MovePivotToAngle(targetAngle),
				() -> Elbow.getInstance().isInShooterCollisionRange()
		);

	}

}
