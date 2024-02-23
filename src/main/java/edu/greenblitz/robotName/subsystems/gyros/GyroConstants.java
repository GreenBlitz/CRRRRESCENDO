package edu.greenblitz.robotName.subsystems.gyros;

import edu.greenblitz.robotName.utils.GBCircle;
import edu.wpi.first.math.geometry.Rotation2d;

public class GyroConstants {

    public static final Rotation2d CIRCLE_CIRCUMFRENCE = Rotation2d.fromRadians(2 * Math.PI);

    public static class PigeonGyro {
        public static final int ID = 12;
    }

    public static class Pigeon2Gyro {
        public static final int ID = 0;
    }
}
