package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class MoveShooterToAngle extends ConditionalCommand {

	public MoveShooterToAngle(Rotation2d targetAngle) {
		super(
				new ParallelCommandGroup(
						new MoveElbowAndWrist(
								ElbowConstants.PresetPositions.SAFE.ANGLE,
								WristConstants.PresetPositions.TRANSFER.ANGLE
						),
						new SequentialCommandGroup(
								new WaitCommand(PivotConstants.DELAY_FOR_NO_COLLISION_SECONDS),
								new MovePivotToAngle(targetAngle)
						)
				),
				new MovePivotToAngle(targetAngle),
				() -> Elbow.getInstance().isInShooterCollisionRange());
	}

}
