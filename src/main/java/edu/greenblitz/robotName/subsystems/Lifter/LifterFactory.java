package edu.greenblitz.robotName.subsystems.Lifter;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Lifter.NeoLifter.NeoLifter;
import edu.greenblitz.robotName.subsystems.Lifter.ReplayLifter.ReplayLifter;
import edu.greenblitz.robotName.subsystems.Lifter.SimulationLifter.SimulationLifter;

public class LifterFactory {
    public static ILifter create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new NeoLifter();
            case REPLAY -> new ReplayLifter();
            case SIMULATION -> new SimulationLifter();
        };
    }
}