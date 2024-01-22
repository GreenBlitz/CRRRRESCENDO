package edu.greenblitz.robotName.subsystems.Lifter;

public class LifterConstants {

    public static final int MOTOR_PORT_ID = 1;

    public static final double PID_KP = 3.6;
    public static final double PID_KI = 1;
    public static final double PID_KD = 0;
    public static final double PID_TOLERANCE = 0.04;
    public static final double MAX_VELOCITY = 5;
    public static final double MAX_ACCELERATION = 10;
    public static final int SWITCH_ID = 1;

    public static final double MOTOR_FINAL_POSITION_WHEN_LIFTING_UP = 5;

    public class SimulationConstants {
        public static final int NUMBER_OF_MOTORS = 0;
        public static final double GEAR_RATIO = 0;
        public static final double MOMENT_OF_INERTIA = 0;
    }
}
