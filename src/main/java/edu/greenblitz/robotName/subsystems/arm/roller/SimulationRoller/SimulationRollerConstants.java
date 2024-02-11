package edu.greenblitz.robotName.subsystems.arm.roller.SimulationRoller;

import edu.greenblitz.robotName.utils.PIDObject;

public class SimulationRollerConstants {

    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 1;

    public static final double MOMENT_OF_INERTIA = 1;

    public static final PIDObject SIMULATION_PID = new PIDObject().withKp(0.3).withKd(0).withMaxPower(1);

}
