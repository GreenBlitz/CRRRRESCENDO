package edu.greenblitz.robotName.subsystems.arm.wrist.simulationWrist;

import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.utils.PIDObject;

public class SimulationWristConstants {

    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 1 / WristConstants.CONVERSION_FACTOR;

    public static final PIDObject SIMULATION_PID = new PIDObject().withKp(0.015).withKd(0).withMaxPower(1);
}