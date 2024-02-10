package edu.greenblitz.robotName.subsystems.shooter.Pivot.SimulationPivot;

import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.PIDObject;

public class SimulationPivotConstants {


    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 1 / PivotConstants.GEAR_RATIO;

    public static final PIDObject SIMULATION_PID = new PIDObject().withKp(0.3).withKd(0).withMaxPower(1);
    
}
