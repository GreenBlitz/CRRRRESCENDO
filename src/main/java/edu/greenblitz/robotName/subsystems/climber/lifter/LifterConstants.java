package edu.greenblitz.robotName.subsystems.climber.lifter;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;

public class LifterConstants {

    public static final double POWER_TO_EXTEND_LIFTER = 0.7;

    public static final double POWER_TO_RETRACT_LIFTER = -0.6;

    public static final double END_OF_CLIMB_POWER = -0.4; //do not increase .

    public static final double POWER_TO_RETRACT_LIFTER_SLOW = -0.1;

    public static final double TIME_TO_RETRACT_SLOW = 1.5;

    public static final double DISTANCE_TO_TRAVEL_SLOW = 5;

    public static final Translation3d ROBOT_RELATIVE_LIFTER_POSITION = new Translation3d(-0.24, 0, 0.35);

    public static final Rotation3d ROBOT_RELATIVE_LIFTER_ROTATION = new Rotation3d(0, Math.PI, -Math.PI / 2);

    public static final double GEAR_RATIO = 1.7458727272727272727272727272727;

    public static final double ENCODER_POSITION_WHEN_RESET = 0;

    public static final double LIFTER_POWER_TO_RELEASES_SOLENOID = -0.3;

    public static final double TOLERANCE = 0.5;

    public static final double LIFTER_RETRACTED_POSITION = 0;

    public static final double LIFTER_EXTENDED_POSITION = 65.5;

    public static final double FORWARD_LIMIT = 67;

    public static final double BACKWARD_LIMIT = -5;

    public static final double STARTING_POSITION = 0;

    public static final double LENGTH_OF_LIFTER = 0.45;

    public static final double LIFTER_MASS_KG = 7;

    public static final double END_OF_CLIMB_CURRENT = 25;

    public static final double LIFTER_JOYSTICK_OFFSET = 0.1;

    public static final double LIFTER_JOYSTICK_NORMALIZER = 4 / 9.0;
}