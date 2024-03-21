package edu.greenblitz.robotName.commands.swerve.rotateTo;

import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;

public class RotateToSpeaker extends MoveByJoysticks {

	public RotateToSpeaker() {
		super(
				DriveMode.NORMAL,
				() -> new RotateToPoint(() -> FieldConstants.MIDDLE_OF_SPEAKER_POSITION.toTranslation2d()).getAsDouble()
		);
	}
}
