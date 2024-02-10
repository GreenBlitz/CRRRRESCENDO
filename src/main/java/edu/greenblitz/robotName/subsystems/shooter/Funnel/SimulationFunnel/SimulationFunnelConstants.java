package edu.greenblitz.robotName.subsystems.shooter.Funnel.SimulationFunnel;

import edu.greenblitz.robotName.utils.PIDObject;
import edu.wpi.first.math.controller.PIDController;

public class SimulationFunnelConstants {

    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 1;

    public static final double MOMENT_OF_INERTIA = 1;

    public static final PIDObject SIMULATION_PID = new PIDObject().withKp(0.3).withKd(0).withMaxPower(1);

}
