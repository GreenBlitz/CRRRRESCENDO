package edu.greenblitz.robotName.subsystems.Lifter;

import edu.greenblitz.robotName.Robot;

public class LifterFactory {
    public static ILifter create() {
        return switch (Robot.RobotType.ROBOT_NAME) {
            case ROBOT_NAME -> new NeoLifter();
            case REPLAY -> new LifterReplay();
            case SIMULATION -> new SimulationLifter();
        };
    }
}
