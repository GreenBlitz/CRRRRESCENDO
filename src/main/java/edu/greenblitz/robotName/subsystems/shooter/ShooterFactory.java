package edu.greenblitz.robotName.subsystems.shooter;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.neoFlyWheel.NeoFlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.simulationFlyWheel.SimulationFlyWheel;

public class ShooterFactory {
    public static IShooter create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new NeoFlyWheel();
            case REPLAY -> new ReplayShooter();
            default -> new SimulationFlyWheel();
        };
    }
}
