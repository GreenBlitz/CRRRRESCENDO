package edu.greenblitz.robotName.subsystems.arm.ArmMechanism;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj.util.Color;

public class ArmMechanismConstants {

    public static final Translation2d SIZE_OF_MECHANISM = new Translation2d(3, 2);

    public static final Translation2d POSITION_OF_MECHANISM = new Translation2d(1.45,0.52);

    public static class WristMechanismConstants {

        public static final int WRIST_LINE_WIDTH = 8;

        public static final Color8Bit COLOR_OF_WRIST = new Color8Bit(Color.kPurple);

    }

    public static class ElbowMechanismConstants {

        public static final int ELBOW_LINE_WIDTH = 8;

        public static final Color8Bit COLOR_OF_ELBOW = new Color8Bit(Color.kRed);

    }

    public static class PivotMechanismConstants {

        public static final Translation2d PIVOT_COORDINATES = new Translation2d(2.04,0.109);

        public static final Color8Bit PIVOT_COLOR =  new Color8Bit(Color.kPurple);

        public static final int LINE_WIDTH = 6;

    }

}
