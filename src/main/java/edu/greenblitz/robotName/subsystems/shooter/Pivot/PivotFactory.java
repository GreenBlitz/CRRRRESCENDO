package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.FalconPivot.FalconPivot;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.SimulationPivot.SimulationPivot;


public class PivotFactory {
    public static IPivot create() {
        return switch (Robot.getRobotType()) {
            case ROBOT_NAME -> new FalconPivot();
            case REPLAY -> new ReplayPivot();
            default -> new SimulationPivot();
        };
    }
}
