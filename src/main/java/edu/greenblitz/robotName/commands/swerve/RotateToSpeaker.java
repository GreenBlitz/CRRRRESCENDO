package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.FieldConstants;

public class RotateToSpeaker extends MoveByJoysticks{

	public RotateToSpeaker() {
		super(
				DriveMode.NORMAL,
				() -> new RotateToPoint(() -> FieldConstants.MIDDLE_OF_RED_SPEAKER_POSITION.toTranslation2d()).getAsDouble()
		);
	}
}
