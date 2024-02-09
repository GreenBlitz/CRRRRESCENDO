package edu.greenblitz.robotName.subsystems.Gyros;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;

public class GyroFactory {

    public static IAngleMeasurementGyro create() {
        return switch (Robot.getRobotType()) {
            case ROBOT_NAME -> new Pigeon2Gyro(GyroConstants.Pigeon2Gyro.ID);
            case REPLAY -> new ReplayGyro();
            case PEGA_SWERVE -> new PigeonGyro(GyroConstants.PigeonGyro.ID);
            case SIMULATION -> new SimulationGyro();
        };
    }
}