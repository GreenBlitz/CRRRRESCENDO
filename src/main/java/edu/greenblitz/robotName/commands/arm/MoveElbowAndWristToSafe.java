package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;

public class MoveElbowAndWristToSafe extends MoveElbowAndWrist {

	public MoveElbowAndWristToSafe() {
		super(
				ElbowConstants.PresetPositions.SAFE.ANGLE,
				WristConstants.PresetPositions.SAFE.ANGLE
		);
	}

}
