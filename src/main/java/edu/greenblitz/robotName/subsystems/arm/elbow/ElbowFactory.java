package edu.greenblitz.robotName.subsystems.arm.elbow;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.elbow.falconElbow.FalconElbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.neoElbow.NeoElbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.simulationElbow.SimulationElbow;

public class ElbowFactory {

    public static IElbow create() {
        return switch (Robot.getRobotType()) {
            case SYNCOPA -> new FalconElbow();
            case REPLAY -> new ReplayElbow();
            case SIMULATION, PEGA_SWERVE -> new SimulationElbow();
        };
    }
}