package edu.greenblitz.robotName.utils;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
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
	 * This is a commonly used activation function in data science.
	 *
	 * @param variable    The variable that is being sigmoided.
	 * @param center      The center of the sigmoid, at this point the value of the function will be equal to 0.5.
	 * @param squishiness How quickly the sigmoid function rises.
	 * @param minHeight   The minimum height of the sigmoid function.
	 * @return The output of the sigmoid.
	 */
	public static double sigmoid(double variable, double center, double squishiness, double minHeight) {
		return 1 + 1 / Math.exp(squishiness * (center - variable)) + minHeight;
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