package edu.greenblitz.robotName.utils;

import edu.wpi.first.math.geometry.Translation2d;

public class GBMath {
    /**
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


    /**
     * Gets a line and a target point and returns the distance between that point and the line.
     *
     * @param startPoint  The first point defining the line.
     * @param endPoint    The second point defining the line.
     * @param targetPoint The point that is distancing the line.
     * @return The minimum distance between the target point and the line between the first two points.
     */
    public static double getDistanceBetweenPointAndLine(Translation2d startPoint, Translation2d endPoint, Translation2d targetPoint) {
        double slope = (startPoint.getY() - endPoint.getY()) / (startPoint.getX() - endPoint.getX());
        double perpendicularSlope = -1 / slope;

        double yIntercept1 = targetPoint.getY() - (targetPoint.getX() * perpendicularSlope);
        double yIntercept2 = startPoint.getY() - (startPoint.getX() * slope);

        double xClosest = (slope - perpendicularSlope) / (yIntercept2 - yIntercept1);
        double yClosest = xClosest * slope + yIntercept2;

        return targetPoint.getDistance(new Translation2d(xClosest, yClosest));
    }

    /**
     * Using a variable it returns the output of the sigmoid function.
     * This is a commonly used activation function in data science, but here
     * it is used to interpolate between the distance of two points and the speed needed
     * to get the first point to that second.
     *
     * @param variable    The variable that is being sigmoided.
     * @param center      The center of the sigmoid, at this point the value of the function will be equal to 0.5.
     * @param squishyness How quickly the sigmoid function rises.
     * @param minHeight   The minimum height of the sigmoid function.
     * @return The output of the sigmoid.
     */
    public static double sigmoid(double variable, double center, double squishyness, double minHeight) {
        return 1 + 1 / Math.exp(squishyness * (center - variable)) + minHeight;
    }

}
