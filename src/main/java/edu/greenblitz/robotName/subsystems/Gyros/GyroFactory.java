package edu.greenblitz.robotName.subsystems.Gyros;

import edu.greenblitz.robotName.RobotConstants;

public class GyroFactory {

    public static IAngleMeasurementGyro create() {
        return switch (RobotConstants.ROBOT_TYPE) {
            case ROBOT_NAME -> new Pigeon2Gyro(GyroConstants.Pigeon2Gyro.ID);
            case REPLAY -> new ReplayGyro();
            case SIMULATION -> new SimulationGyro();
        };
    }
}