package edu.greenblitz.robotName;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class ScoringModeSelector {

	private static ScoringMode currentMode;

	public static void init() {
		if (currentMode == null) {
			currentMode = ScoringMode.SPEAKER;
		}
	}

	public static ScoringMode getScoringMode() {
		init();
		return currentMode;
	}

	public static void setScoringMode(ScoringMode scoringMode) {
		currentMode = scoringMode;
	}

	public static boolean isSpeakerMode() {
		return getScoringMode() == ScoringMode.SPEAKER;
	}

	public static boolean isAmpMode() {
		return getScoringMode() == ScoringMode.AMP;
	}

	public static boolean isClimbMode() {
		return getScoringMode() == ScoringMode.CLIMB;
	}

}
