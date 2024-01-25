package edu.greenblitz.robotName.subsystems.Shooter.FlyWheel;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.NeoFlyWheel.NeoFlyWheel;
import edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.simulationFlyWheel.SimulationFlyWheel;

public class FlyWheelFactory {
    public static IFlyWheel create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new NeoFlyWheel();
            case REPLAY -> new ReplayFlyWheel();
            case SIMULATION -> new SimulationFlyWheel();
        };
    }
}
