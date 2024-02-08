package edu.greenblitz.robotName.subsystems.arm.elbow.SimulationElbow;

import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.utils.PIDObject;

public class SimulationElbowConstants {

    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 1 / ElbowConstants.GEAR_RATIO;

    public static final PIDObject SIMULATION_PID = new PIDObject().withKp(0.15).withKd(0.2).withMaxPower(1);

}
