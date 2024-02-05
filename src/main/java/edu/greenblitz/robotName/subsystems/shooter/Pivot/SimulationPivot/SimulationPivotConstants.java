package edu.greenblitz.robotName.subsystems.shooter.Pivot.SimulationPivot;

import edu.greenblitz.robotName.utils.PIDObject;
import edu.wpi.first.math.geometry.Rotation2d;

import static edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants.RELATIVE_POSITION_CONVERSION_FACTOR;


public class SimulationPivotConstants {


    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 1 / RELATIVE_POSITION_CONVERSION_FACTOR;

    public static final PIDObject SIMULATION_PID = new PIDObject().withKp(0.3).withKd(0).withMaxPower(1);

    public static final Rotation2d SIMULATION_CONVERSION_PIVOT_ANGLE =Rotation2d.fromDegrees(90);

}
