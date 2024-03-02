package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToClimb;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PrepareToClimb extends ParallelCommandGroup {
	public PrepareToClimb(){
		super(
				new MovePivotToAngle(PivotConstants.PresetPositions.CLOSE_SHOOTING.ANGLE),
				new MoveElbowAndWristToClimb()
		);
	}
}
