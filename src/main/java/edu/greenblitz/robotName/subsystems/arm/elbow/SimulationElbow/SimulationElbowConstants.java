package edu.greenblitz.robotName.subsystems.arm.elbow.SimulationElbow;

import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.util.Units;

public class SimulationElbowConstants {

    public static final SimpleMotorFeedforward SIMPLE_MOTOR_FEEDFORWARD = new SimpleMotorFeedforward(0,0);

    public static final int NUMBER_OF_MOTORS = 1;

    public static final double GEAR_RATIO = 1 / ElbowConstants.GEAR_RATIO;

    public static final PIDObject SIMULATION_PID = new PIDObject().withKp(0.3).withKd(0).withMaxPower(1);

    public static final double MECHANISM_NAME_TO_ROBOT_TRANSLATION = Units.degreesToRadians(-90);

}
