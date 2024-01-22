package edu.greenblitz.robotName.subsystems.Arm.ElbowUtils.SimulationElbow;

import edu.greenblitz.robotName.subsystems.Arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.utils.PIDObject;

public class SimulationElbowConstants {

    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 1 / ElbowConstants.RELATIVE_POSITION_CONVERSION_FACTOR;

    public static final PIDObject SIM_PID = new PIDObject().withKp(0.8).withKd(0.3).withMaxPower(1);

}
