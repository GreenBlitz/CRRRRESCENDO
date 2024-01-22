package edu.greenblitz.robotName.subsystems.Arm.EndEffector.WristUtils.SimulationWrist;

import edu.greenblitz.robotName.subsystems.Arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.utils.PIDObject;

public class SimulationWristConstants {
    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 1 / WristConstants.RELATIVE_POSITION_CONVERSION_FACTOR;

    public static final PIDObject SIMULATION_PID = new PIDObject().withKp(0.8).withKd(0.3).withMaxPower(1);

}
