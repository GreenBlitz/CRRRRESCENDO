package edu.greenblitz.robotName.subsystems.arm.ElbowUtils;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.FalconElbow.FalconElbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.SimulationElbow.SimulationElbow;

public class ElbowFactory {
    public static IElbow create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new FalconElbow();
            case REPLAY -> new ReplayElbow();
            default -> new SimulationElbow();
        };
    }
}
