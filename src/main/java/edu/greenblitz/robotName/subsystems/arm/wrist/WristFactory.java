package edu.greenblitz.robotName.subsystems.arm.wrist;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.NeoWrist.NeoWrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.SimulationWrist.SimulationWrist;

public class WristFactory {
    public static IWrist create() {
        return switch (Robot.getRobotType()) {
            case ROBOT_NAME -> new NeoWrist();
            case REPLAY -> new ReplayWrist();
            case SIMULATION, PEGA_SWERVE -> new SimulationWrist();
        };
    }
}
