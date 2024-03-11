package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.roller.ReleaseNoteFromRollerToTrap;
import edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ScoreToTrap extends SequentialCommandGroup {

	public ScoreToTrap() {
		super(
				new MoveWristToAngle(WristConstants.PresetPositions.SCORE_TRAP),
				new ReleaseNoteFromRollerToTrap()
		);
	}

}
