package edu.greenblitz.robotName.subsystems.arm.wrist;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.wrist.simulationWrist.SimulationWrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.srxWrist.SRXWrist;

public class WristFactory {

    public static IWrist create() {
        return switch (Robot.getRobotType()) {
            case SYNCOPA -> new SRXWrist();
            case REPLAY -> new ReplayWrist();
            case SIMULATION, PEGA_SWERVE -> new SimulationWrist();
        };
    }
}