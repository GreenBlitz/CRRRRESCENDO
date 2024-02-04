package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.FalconPivot.FalconPivot;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.SimulationPivot.SimulationPivot;


public class PivotFactory {
    public static IPivot create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new FalconPivot();
            case SIMULATION -> new SimulationPivot();
            default -> new ReplayPivot();
        };
    }
}
