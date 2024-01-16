package edu.greenblitz.robotName.subsystems.Arm.Elbow;

import edu.greenblitz.robotName.utils.PIDObject;
import edu.wpi.first.math.util.Units;

public class ElbowConstants {

    public static final double ARM_LENGTH = 1;
    public static final double ARM_MASS_KG = 1;
    public static final double BACKWARD_ANGLE_LIMIT = 1;
    public static final double FORWARD_ANGLE_LIMIT = 1;
    public static final double RELATIVE_POSITION_CONVERSION_FACTOR = 1;
    public static final double STARTING_ANGLE = 1;


    public static class Simulation {
        public static final int NUMBER_OF_MOTORS = 1;
        public static final double GEAR_RATIO = 1 / RELATIVE_POSITION_CONVERSION_FACTOR;

        public static final PIDObject SIM_PID = new PIDObject().withKp(0.8).withKd(0.3).withMaxPower(1);
        public static final double SIM_ANGLE_TOLERANCE = Units.degreesToRadians(3);
    }
}
