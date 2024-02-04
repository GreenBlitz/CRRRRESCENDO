package edu.greenblitz.robotName.utils;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class GBMath {
    /**
     *
     * modulo (2,6) -> 0
     * modulo (2,3) -> 1
     * modulo (-2,3) -> 1
     * modulo (3,-2) -> -1 -> 1
     *
     * @param x the number to be modulu-ed
     * @param y the modulo base
     * @return the correct modulo result
     */
    public static double absoluteModulo(double x, double y) {
        return ((x % y) + y) % y;
    }

    public static double calculateDifferenceX(Pose2d pos1, Pose2d pos2){
        return Math.abs(pos1.getX()-pos2.getX());
    }
    public static double calculateDifferenceY(Pose2d pos1, Pose2d pos2){
        return Math.abs(pos1.getY()-pos2.getY());
    }

    public static boolean isPositionWithinTolerance(Pose2d pos1, Pose2d pos2, double tolerance){
        return calculateDifferenceX(pos1,pos2)<=tolerance && calculateDifferenceY(pos1,pos2)<=tolerance;
    }
}