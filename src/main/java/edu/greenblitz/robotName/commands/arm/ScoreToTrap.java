package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;

public class ScoreToTrap extends MoveWristToAngle { //need to also bring note back

	public ScoreToTrap() {
		super(WristConstants.PresetPositions.SCORE_CLIMB);
	}
}
