package edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.NeoWrist.NeoWrist;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.SimulationWrist.SimulationWrist;

public class WristFactory {
    public static IWrist create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new NeoWrist();
            case REPLAY -> new ReplayWrist();
            case SIMULATION -> new SimulationWrist();
        };
    }
}
