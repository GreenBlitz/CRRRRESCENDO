package edu.greenblitz.robotName.subsystems.Shooter.Pivot;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Shooter.Pivot.MotorPivot.FalconPivot.FalconPivot;
import edu.greenblitz.robotName.subsystems.Shooter.Pivot.SimulationPivot.SimulationPivot;


public class PivotFactory {
    public static IPivot create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new FalconPivot();
            case SIMULATION -> new SimulationPivot();
            case REPLAY -> new ReplayPivot();
        };
    }
}
