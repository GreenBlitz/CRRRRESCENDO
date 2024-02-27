package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.arm.MoveArmToClimb;
import edu.greenblitz.robotName.commands.lifter.MoveToClimb;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class getToClimbPosition extends ParallelCommandGroup {

	public getToClimbPosition() {
		super(
				new MovePivotToAngle(PivotConstants.FORWARD_ANGLE_LIMIT),
				new MoveArmToClimb(),
				new MoveToClimb()
		);
	}
}
