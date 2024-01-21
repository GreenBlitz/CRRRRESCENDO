package edu.greenblitz.robotName.subsystems.Arm.Wrist;

import edu.greenblitz.robotName.RobotConstants;

public class WristFactory {
    public static IWrist create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new NeoWrist();
            case SIMULATION -> new SimulationWrist();
            default -> new ReplayWrist();
        };
    }
}
