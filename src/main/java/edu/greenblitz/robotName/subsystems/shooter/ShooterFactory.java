package edu.greenblitz.robotName.subsystems.shooter;

import edu.greenblitz.robotName.RobotConstants;

public class ShooterFactory {
    public static IShooter create() {
        return switch (RobotConstants.ROBOT_TYPE) {
//            case ROBOT_NAME -> new NeoShooter();
//            case SIMULATION -> new ReplayShooter();
            default -> new SimulationShooter();
        };
    }
}
