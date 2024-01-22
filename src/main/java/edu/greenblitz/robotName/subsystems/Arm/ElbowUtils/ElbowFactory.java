package edu.greenblitz.robotName.subsystems.Arm.ElbowUtils;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Arm.ElbowUtils.FalconElbow.FalconElbow;
import edu.greenblitz.robotName.subsystems.Arm.ElbowUtils.SimulationElbow.SimulationElbow;

public class ElbowFactory {
    public static IElbow create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new FalconElbow();
            case REPLAY -> new ReplayElbow();
            case SIMULATION -> new SimulationElbow();
        };
    }
}
