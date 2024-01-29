package edu.greenblitz.robotName;

import edu.greenblitz.robotName.utils.ScoringMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ScoringModeSelector {

    private static ScoringMode currentMode;

    public static ScoringMode getScoringMode() {
        if (currentMode == null) {
            currentMode = ScoringMode.SPEAKER;
        }
        return currentMode;
    }

    public static void setScoringMode(ScoringMode scoringMode) {
        SmartDashboard.putString("Scoring Mode", currentMode.toString());
        currentMode = scoringMode;
    }

    public static boolean isSpeakerToAmp() {
        return getScoringMode() == ScoringMode.SPEAKER;
    }
}
