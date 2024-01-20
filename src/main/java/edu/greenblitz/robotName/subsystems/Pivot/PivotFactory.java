package edu.greenblitz.robotName.subsystems.Pivot;

import edu.greenblitz.robotName.RobotConstants;

public class PivotFactory {
    public static IPivot create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new FalconPivot();
            case SIMULATION -> new SimulationPivot();
            default -> new ReplayPivot();
        };
    }
}
