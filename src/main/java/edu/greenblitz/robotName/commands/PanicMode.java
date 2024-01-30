package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.GetNoteToSystem.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToSafe;
import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class PanicMode extends ParallelCommandGroup {

	public PanicMode() {
		super(
				new MoveShooterToAngle(PivotConstants.PresetPositions.SAFE.ANGLE),
				new ConditionalCommand(
						new GBCommand() {},
						new MoveElbowAndWristToSafe(),
						() -> Elbow.getInstance().isInShooterCollisionRange()
				)
		);
	}
}
