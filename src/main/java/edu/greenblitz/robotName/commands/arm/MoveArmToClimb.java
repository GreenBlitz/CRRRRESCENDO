package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;

public class MoveArmToClimb extends MoveElbowAndWrist {

	public MoveArmToClimb() {
		super(
				ElbowConstants.PresetPositions.CLIMB,
				WristConstants.PresetPositions.CLIMB
		);
	}
}
