package edu.greenblitz.robotName.subsystems.Shooter.Mechanism;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;

public class ShooterMechanismConstants {

    public static final Translation2d SIZE_OF_MECHANISM = new Translation2d(2.0, 2.0);

    public static class PivotMechanismConstants {

        public static final Translation2d PIVOT_COORDINATES = new Translation2d(0.5,0.5);

        public static final Color8Bit PIVOT_COLOR =  new Color8Bit(Color.kPurple);

        public static final int LINE_WIDTH = 6;

    }


}