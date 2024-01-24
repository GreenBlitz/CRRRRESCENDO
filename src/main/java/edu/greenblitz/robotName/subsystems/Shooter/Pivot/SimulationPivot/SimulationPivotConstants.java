package edu.greenblitz.robotName.subsystems.Shooter.Pivot.SimulationPivot;

import edu.greenblitz.robotName.utils.PIDObject;

import static edu.greenblitz.robotName.subsystems.Shooter.Pivot.PivotConstants.RELATIVE_POSITION_CONVERSION_FACTOR;


public class SimulationPivotConstants {


    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 1 / RELATIVE_POSITION_CONVERSION_FACTOR;

    public static final PIDObject SIMULATION_PID = new PIDObject().withKp(0.8).withKd(0.3).withMaxPower(1);


}
