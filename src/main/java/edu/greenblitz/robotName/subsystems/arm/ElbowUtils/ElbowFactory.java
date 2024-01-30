package edu.greenblitz.robotName.subsystems.arm.ElbowUtils;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.MotorElbow.FalconElbow.FalconElbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.MotorElbow.NeoElbow.NeoElbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.SimulationElbow.SimulationElbow;

public class ElbowFactory {
    public static IElbow create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new NeoElbow();
            case REPLAY -> new ReplayElbow();
            case SIMULATION -> new SimulationElbow();
        };
    }
}
