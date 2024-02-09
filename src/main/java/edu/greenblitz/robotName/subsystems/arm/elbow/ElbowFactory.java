package edu.greenblitz.robotName.subsystems.arm.elbow;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.elbow.NeoElbow.NeoElbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.SimulationElbow.SimulationElbow;

public class ElbowFactory {
    public static IElbow create() {
        return switch (Robot.getRobotType()) {
            case ROBOT_NAME -> new NeoElbow();
            case REPLAY -> new ReplayElbow();
            default -> new SimulationElbow();
        };
    }
}
