package edu.greenblitz.robotName.subsystems.Arm.ArmMechanism;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Color8Bit;

public class ArmMechanismConstants {

    public static final Translation2d SIZE_OF_MECHANISM = new Translation2d(1,1);

    public class WristMechanismConstants{

        public static final Translation2d WRIST_POSITION = new Translation2d(0.5,0.5);

        public static final int LINE_WIDTH = 8;

        public static final Color8Bit COLOR_OF_WRIST = new Color8Bit(edu.wpi.first.wpilibj.util.Color.kPurple);
    }



}
