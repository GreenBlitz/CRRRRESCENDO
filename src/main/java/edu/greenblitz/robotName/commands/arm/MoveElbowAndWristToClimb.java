package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;

public class MoveElbowAndWristToClimb extends MoveElbowAndWrist {

    public MoveElbowAndWristToClimb() {
		super(
				ElbowConstants.PresetPositions.CLIMB.ANGLE,
				WristConstants.PresetPositions.SCORE_TRAP.ANGLE
		);
	}
}
