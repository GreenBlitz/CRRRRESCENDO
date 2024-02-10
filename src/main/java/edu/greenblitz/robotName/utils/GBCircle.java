package edu.greenblitz.robotName.utils;

import edu.wpi.first.math.geometry.Translation2d;

/**
 * A circle.
 * A radius and center position, EPSILON for when the given point is right below the center (Cx == Px)
 */
public class GBCircle {
    private Translation2d centerPosition;
    private double radius;
    private final static double EPSILON = 0.1;

    /**
     * Constructor using the center and radius.
     *
     * @param position The position of the center of the circle.
     * @param radius   The radius of the circle.
     */
    public GBCircle(Translation2d position, double radius) {
        this.centerPosition = position;
        this.radius = radius;
    }

    /**
     * Setter for the circle center.
     *
     * @param position The position of the center of the circle.
     */
    public void setCenterPosition(Translation2d position) {
        this.centerPosition = position;
    }

    /**
     * Setter for the circle radius.
     *
     * @param radius The radius of the circle.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Getter for the circle center.
     *
     * @return The center of the circle.
     */
    public Translation2d getCenterPosition() {
        return centerPosition;
    }

    /**
     * Getter for the circle radius.
     *
     * @return The radius of the circle.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Receives a point and returns true if the position is inside (included) or on the circle.
     *
     * @param position The position of the point.
     * @return True if the point is inside (included) or on the circle, false if the point is outside.
     */
    public boolean isInCircle(Translation2d position) {
        return Math.pow(centerPosition.getX() - position.getX(), 2) + Math.pow(centerPosition.getY() - position.getY(), 2) <= radius * radius;
    }

    /**
     * Receives a point, returns the point on the rim of the circle which is closest to the given point.
     * <p>
     * Calculates the slope between the center of the circle and a given point,
     * and using trigonometry calculates the points on the circle that intersect with the linear slope graph.
     * If the Y value of the point is below the center, returns the negative x and y value and vice versa.
     * <p>
     * * If the point and the circle center have the same X value, adds epsilon to the given position (so the calculation doesn't fuck up)
     *
     * @param position The position of the point.
     * @return The position of the closest point.
     */
    public Translation2d getClosestCircleRimPosition(Translation2d position) {
        double deltaY = position.getY() - centerPosition.getY();
        double deltaX = position.getX() - centerPosition.getX();
        if (deltaX == 0) {
            deltaX = EPSILON;
        }

        double slope = deltaY / deltaX;
        double angleOfSlope = Math.atan(slope);

        double targetX = radius * Math.cos(angleOfSlope) * Math.signum(deltaX) + centerPosition.getX();
        double targetY = radius * Math.sin(angleOfSlope) * Math.signum(deltaX) + centerPosition.getY();

        return new Translation2d(targetX, targetY);
    }
}