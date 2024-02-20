package edu.greenblitz.robotName.subsystems.arm.wrist;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.wrist.bagWrist.BagWrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.neoWrist.NeoWrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.simulationWrist.SimulationWrist;

public class WristFactory {

    public static IWrist create() {
        return switch (Robot.getRobotType()) {
            case SYNCOPA -> new BagWrist();
            case REPLAY -> new ReplayWrist();
            case SIMULATION, PEGA_SWERVE -> new SimulationWrist();
        };
    }
}