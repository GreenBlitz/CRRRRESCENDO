package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.simulationFlyWheel;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.NeoFlyWheel.NeoFlyWheelConstants;
import edu.wpi.first.math.controller.PIDController;

public class SimulationFlyWheelConstants extends FlyWheelConstants {

    public static class RightMotor {

        public static final int NUMBER_OF_MOTORS = 1;

        public static final double GEARING = 1.0 / NeoFlyWheelConstants.RightMotor.GEAR_RATIO;

        public static final double MOMENT_OF_INERTIA = 0.01;

        public static final double POWER_TO_VELOCITY_FACTOR = 0.1;

        public static final PIDController SIMULATION_PID = new PIDController(0.3,0,0.3);

    }
    public static class LeftMotor{

        public static final int NUMBER_OF_MOTORS = 1;

        public static final double GEARING =  1.0 / NeoFlyWheelConstants.LeftMotor.GEAR_RATIO;

        public static final double MOMENT_OF_INERTIA = 0.01;

        public static final double POWER_TO_VELOCITY_FACTOR = 0.1;

        public static final PIDController SIMULATION_PID = new PIDController(10,0,0.3);
    }
}
