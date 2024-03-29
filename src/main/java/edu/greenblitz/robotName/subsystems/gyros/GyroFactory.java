package edu.greenblitz.robotName.subsystems.gyros;

import edu.greenblitz.robotName.Robot;

public class GyroFactory {

    public static IAngleMeasurementGyro create() {
        return switch (Robot.getRobotType()) {
            case SYNCOPA -> new Pigeon2Gyro(GyroConstants.Pigeon2Gyro.ID);
            case REPLAY -> new ReplayGyro();
            case PEGA_SWERVE -> new PigeonGyro(GyroConstants.PigeonGyro.ID);
            case SIMULATION -> new SimulationGyro();
        };
    }
}