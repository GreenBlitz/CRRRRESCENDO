package edu.greenblitz.robotName.subsystems.Arm.ArmMechanism;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj.util.Color;

public class ArmMechanismConstants {

    public static final Translation2d SIZE_OF_MECHANISM = new Translation2d(2, 2);

    public static final Translation2d POSITION_OF_MECHANISM = SIZE_OF_MECHANISM.div(2);

    public static class WristMechanismConstants {

        public static final int WRIST_LINE_WIDTH = 8;

        public static final Color8Bit COLOR_OF_WRIST = new Color8Bit(Color.kPurple);

    }

    public static class ElbowMechanismConstants {

        public static final int ELBOW_LINE_WIDTH = 8;

        public static final Color8Bit COLOR_OF_ELBOW = new Color8Bit(Color.kRed);

    }

}
