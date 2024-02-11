package edu.greenblitz.robotName.utils;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

/**
 * A circle.
 * A radius and center position, EPSILON for when the given point is right below the center (Cx == Px),
 * and an upper and lower angle limits to set partial circles i.e. half circles, quarter circles...
 */
public class GBCircle {
    private Translation2d centerPosition;
    private double radius;
    private Rotation2d lowerAngleLimit;
    private Rotation2d upperAngleLimit;
    private final static double EPSILON = 0.1;

    /**
     * Constructor using the center and radius.
     *
     * @param position The position of the center of the circle.
     * @param radius   The radius of the circle.
     */
    public GBCircle(Translation2d position, double radius) {
        this(position, radius, Rotation2d.fromRotations(0), Rotation2d.fromRotations(1));
    }

    /**
     * Constructor using the center, radius, the lower angle limit and the upper angle limit.
     * Example for using the angle limits to get a half circle:
     * GBCircle halfCircle = new GBCircle(new Translation2d(0,0),1,Rotation.fromDegrees(0),Rotation.fromDegrees(180));
     *
     * @param position        The position of the center of the circle.
     * @param radius          The radius of the circle.
     * @param lowerAngleLimit The lower angle limit of the circle.
     * @param upperAngleLimit The upper angle limit of the circle.
     */
    public GBCircle(Translation2d position, double radius, Rotation2d lowerAngleLimit, Rotation2d upperAngleLimit) {
        this.centerPosition = position;
        this.radius = radius;
        this.lowerAngleLimit = lowerAngleLimit;
        this.upperAngleLimit = upperAngleLimit;
    }

    /**
     * Setter for the circle uppwer angle limit.
     *
     * @param upperAngleLimit The upper angle limit of the circle.
     */
    public void setUpperAngleLimit(Rotation2d upperAngleLimit) {
        this.upperAngleLimit = upperAngleLimit;
    }

    /**
     * Setter for the circle lower angle limit.
     *
     * @param lowerAngleLimit The lower angle limit of the circle.
     */
    public void setLowerAngleLimit(Rotation2d lowerAngleLimit) {
        this.lowerAngleLimit = lowerAngleLimit;
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
     * Getter for the circle upper angle limit.
     *
     * @return The upper angle limit.
     */
    public Rotation2d getUpperAngleLimit() {
        return upperAngleLimit;
    }

    /**
     * Getter for the circle lower angle limit.
     *
     * @return The lower angle limit.
     */
    public Rotation2d getLowerAngleLimit() {
        return lowerAngleLimit;
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
        angleOfSlope = MathUtil.clamp(angleOfSlope, lowerAngleLimit.getRadians(), upperAngleLimit.getRadians());

        double targetX = radius * Math.cos(angleOfSlope) * Math.signum(deltaX) + centerPosition.getX();
        double targetY = radius * Math.sin(angleOfSlope) * Math.signum(deltaX) + centerPosition.getY();

        return new Translation2d(targetX, targetY);
    }
}