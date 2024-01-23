package edu.greenblitz.robotName.utils;

import edu.greenblitz.robotName.FieldConstants;
import edu.wpi.first.math.geometry.Rotation2d;
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
     * Gets two points and returns the distance between them.
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
     * Gets two points and returns the distance between them.
     *
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The distance between the two points.
     */
    public static double distance(Translation2d p1, Translation2d p2) {
        return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }
    /**
     * Gets two points and returns the distance between them.
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
     * Gets two points and returns the distance between them.
     *
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The distance between the two points.
     */
    public static double distance(Translation3d p1, Translation3d p2) {
        return distance(p1.getX(), p1.getY(), p1.getZ(), p2.getX(), p2.getY(), p2.getZ());
    }
    /**
     * A circle.
     * A radius and position.
     */
    public static class GBCircle {
        private Translation2d centerPosition;
        private double radius;
        private final double EPSILON;
        /**
         * Constructor using the center and radius.
         * @param position The position of the center of the circle.
         * @param radius The radius of the circle.
         */
        public GBCircle(Translation2d position, double radius) {
            this.centerPosition = position;
            this.radius = radius;
            EPSILON = 0.1;
        }
        /**
         * Setter for the circle center.
         * @param position The position of the center of the circle.
         */
        public void setCenterPosition(Translation2d position) {
            this.centerPosition = position;
        }
        /**
         * Setter for the circle radius.
         * @param radius The radius of the circle.
         */
        public void setRadius(double radius) {
            this.radius = radius;
        }
        /**
         * Getter for the circle radius.
         * @return The radius of the circle.
         */
        public double getRadius() {
            return radius;
        }
        /**
         * Getter for the circle center.
         * @return The center of the circle.
         */
        public Translation2d getCenterPosition() {
            return centerPosition;
        }
        /**
         * Gets a point and returns true if the position is inside (included) the circle.
         *
         * @param position The position of the point.
         * @return True if the point is inside (included) the circle, false if the point is outside.
         */
        public boolean isInCircle(Translation2d position) {
            return Math.pow(centerPosition.getX() - position.getX(), 2) + Math.pow(centerPosition.getY() - position.getY(), 2) <= radius * radius;
        }
        /**
         * Gets a point and returns the point on the rim of the circle which is closest to the point.
         *
         * @param position The position of the point.
         * @return The position of the closest point.
         */
        public Translation2d getClosestPositionOnBorder(Translation2d position) {
            if (position.getX() == centerPosition.getX()) {
                return position.plus(new Translation2d(EPSILON, 0));
            }
            double slope = ((centerPosition.getY() - position.getY()) / centerPosition.getX() - position.getX());
            double angle = Math.atan(slope);
            double x = radius * Math.cos(angle) * Math.signum(position.getX() - centerPosition.getX()) + centerPosition.getX();
            double y = radius * Math.sin(angle) * Math.signum(position.getX() - centerPosition.getX()) + centerPosition.getY();
            return new Translation2d(x, y);
        }
    }
}
