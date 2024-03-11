package edu.greenblitz.robotName.commands.climbing;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToClimb;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class PrepareToClimb extends ParallelCommandGroup {

	public PrepareToClimb() {
		super(
				new MovePivotToAngle(PivotConstants.PresetPositions.SAFE.ANGLE),
				new MoveElbowAndWristToClimb()
		);
	}
}
