package edu.greenblitz.robotName.subsystems.arm.roller;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.roller.bagRoller.BagRoller;
import edu.greenblitz.robotName.subsystems.arm.roller.neoRoller.NeoRoller;
import edu.greenblitz.robotName.subsystems.arm.roller.simulationRoller.SimulationRoller;

public class RollerFactory {

    public static IRoller create() {
        return switch (Robot.getRobotType()) {
            case SYNCOPA -> new BagRoller();
            case REPLAY -> new ReplayRoller();
            case SIMULATION, PEGA_SWERVE -> new SimulationRoller();
        };
    }
}