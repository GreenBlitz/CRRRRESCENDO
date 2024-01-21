package edu.greenblitz.robotName.subsystems.Arm.EndEffector;

import edu.greenblitz.robotName.RobotConstants;

public class EndEffectorFactory {
    public static IEndEffector create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new NeoEndeffector();
            case SIMULATION -> new SimulationEndEffector();
            default -> new ReplayEndEffector();
        };
    }
}
