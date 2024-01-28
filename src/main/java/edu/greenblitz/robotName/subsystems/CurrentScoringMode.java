package edu.greenblitz.robotName.subsystems;

public class CurrentScoringMode {
    public enum ScoringMode {
        SPEAKER,
        AMP,
        IN_BETWEEN
    }

    private static ScoringMode current;

    public static ScoringMode getCurrentScoringMode() {
        if (current == null)
            current = ScoringMode.SPEAKER;
        return current;
    }

    public static void setCurrentScoringMode(ScoringMode scoringMode) {
        current = scoringMode;
    }
}
