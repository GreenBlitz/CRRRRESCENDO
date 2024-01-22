package edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist.SimulationWrist;

import edu.greenblitz.robotName.utils.PIDObject;

import static edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist.WristConstants.RELATIVE_POSITION_CONVERSION_FACTOR;

public class SimulationWristConstants {
    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 1 / RELATIVE_POSITION_CONVERSION_FACTOR;

    public static final PIDObject SIM_PID = new PIDObject().withKp(0.8).withKd(0.3).withMaxPower(1);

}
