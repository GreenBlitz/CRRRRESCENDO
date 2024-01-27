package edu.greenblitz.robotName.utils;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;

public class GBMath {

    /**
     * modulo (2,6) -> 0 * modulo (2,3) -> 1
     * modulo (-2,3) -> 1 * modulo (3,-2) -> -1 -> 1
     *
     * @param x the number to be modulu-ed
     * @param y the modulo base
     * @return the correct modulo result
     */
    public static double absoluteModulo(double x, double y) {
        return ((x % y) + y) % y;
    }

    /**
     * Receives two points and returns the distance between them.
     *
     * @param x1 The x of the first point.
     * @param y1 The y of the first point.
     * @param x2 The x of the second point.
     * @param y2 The y of the second point.
     * @return The distance between the two points.
     */
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Receives two points and returns the distance between them.
     *
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The distance between the two points.
     */
    public static double distance(Translation2d p1, Translation2d p2) {
        return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * Receives two points and returns the distance between them.
     *
     * @param x1 The x of the first point.
     * @param y1 The y of the first point.
     * @param z1 The z of the first point.
     * @param x2 The x of the second point.
     * @param y2 The y of the second point.
     * @param z2 The z of the second point.
     * @return The distance between the two points.
     */
    public static double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
    }

    /**
     * Receives two points and returns the distance between them.
     *
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The distance between the two points.
     */
    public static double distance(Translation3d p1, Translation3d p2) {
        return distance(p1.getX(), p1.getY(), p1.getZ(), p2.getX(), p2.getY(), p2.getZ());
    }
}