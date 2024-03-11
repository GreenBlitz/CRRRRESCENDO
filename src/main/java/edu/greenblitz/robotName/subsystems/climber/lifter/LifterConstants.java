package edu.greenblitz.robotName.subsystems.climber.lifter;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;

public class LifterConstants {

    public static final double POWER_TO_EXTEND_LIFTER = 0.4;

    public static final double POWER_TO_RETRACT_LIFTER = -0.4;

    public static final double TIME_TO_RELEASE_LIFTER = 0.2; //find

    public static final Translation3d ROBOT_RELATIVE_LIFTER_POSITION = new Translation3d(-0.24, 0, 0.35);

    public static final Rotation3d ROBOT_RELATIVE_LIFTER_ROTATION = new Rotation3d(0, Math.PI, -Math.PI / 2);

    public static final double GEAR_RATIO = 1.7458727272727272727272727272727;

    public static final double ENCODER_POSITION_WHEN_RESET = 0;

    public static final double LIFTER_POWER_TO_RELEASES_SOLENOID = -0.3;

    public static final double TOLERANCE = 0.5;

    public static final double LIFTER_RETRACTED_POSITION = 0;

    public static final double LIFTER_EXTENDED_POSITION = 60;

    public static final double FORWARD_LIMIT = 63;

    public static final double BACKWARD_LIMIT = -1;

    public static final double STARTING_POSITION = 0;

    public static final double LENGTH_OF_LIFTER = 0.45;

    public static final double LIFTER_MASS_KG = 7;
}