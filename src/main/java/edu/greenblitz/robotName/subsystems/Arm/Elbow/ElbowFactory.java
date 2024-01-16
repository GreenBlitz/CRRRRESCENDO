package edu.greenblitz.robotName.subsystems.Arm.Elbow;

import edu.greenblitz.robotName.RobotConstants;

public class ElbowFactory {
    public static IElbow create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new NeoElbow();
            case REPLAY -> new ReplayElbow();
            default -> new SimulationElbow();
        };
    }
}
