package edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist.NeoWrist.NeoWrist;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist.SimulationWrist.SimulationWrist;

public class WristFactory {
    public static IWrist create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new NeoWrist();
            case REPLAY -> new ReplayWrist();
            default -> new SimulationWrist();
        };
    }
}
