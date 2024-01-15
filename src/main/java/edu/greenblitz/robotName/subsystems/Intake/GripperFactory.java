package edu.greenblitz.robotName.subsystems.Intake;

import edu.greenblitz.robotName.RobotConstants;

public class GripperFactory {

    public static IGripper create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new NeoGripper();
            case REPLAY -> new ReplayGripper();
            default -> new SimulationGripper();
        };
    }
}
