package edu.greenblitz.robotName;

import edu.greenblitz.robotName.utils.ScoringMode;

public class ScoringModeSelector {

    private static ScoringMode currentMode;

    public static ScoringMode getScoringMode() {
        if (currentMode == null) {
            currentMode = ScoringMode.SPEAKER;
        }
        return currentMode;
    }

    public static void setScoringMode(ScoringMode scoringMode) {
        System.out.println(scoringMode);
        currentMode = scoringMode;
    }

    public static boolean isSpeakerMode() {
        return getScoringMode() == ScoringMode.SPEAKER;
    }

    public static boolean isAmpMode() {
        return getScoringMode() == ScoringMode.AMP;
    }
}
