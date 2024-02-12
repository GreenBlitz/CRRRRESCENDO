package edu.greenblitz.robotName.subsystems.shooter.FlyWheel;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FalconFlyWheel.FalconFlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.NeoFlyWheel.NeoFlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.simulationFlyWheel.SimulationFlyWheel;

public class FlyWheelFactory {
    public static IFlyWheel create() {
        return switch (Robot.getRobotType()) {
            case ROBOT_NAME, PEGA_SWERVE -> new FalconFlyWheel();
            case REPLAY -> new ReplayFlyWheel();
            case SIMULATION -> new SimulationFlyWheel();
        };
    }
}
