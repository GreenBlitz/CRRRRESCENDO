package edu.greenblitz.robotName.subsystems.shooter.FlyWheel;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.NeoFlyWheel.NeoFlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.simulationFlyWheel.SimulationFlyWheel;

public class FlyWheelFactory {
    public static IFlyWheel create() {
        return switch (Robot.getRobotType()) {
            case ROBOT_NAME, PEGA_SWERVE -> new NeoFlyWheel();
            case REPLAY -> new ReplayFlyWheel();
            case SIMULATION -> new SimulationFlyWheel();
        };
    }
}
