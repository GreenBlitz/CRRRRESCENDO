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

    public static class GBCircle {
        private Translation2d centerPosition;
        private double radius;
        private final double EPSILON;

        public GBCircle(Translation2d translation, double radius) {
            this.centerPosition = translation;
            this.radius = radius;
            EPSILON = 0.1;
        }

        public void setCenterPosition(Translation2d centerPosition) {
            this.centerPosition = centerPosition;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        public Translation2d getCenterPosition() {
            return centerPosition;
        }

        public boolean isInCircle(Translation2d position) {
            return Math.pow(centerPosition.getX() - position.getX(), 2) + Math.pow(centerPosition.getY() - position.getY(), 2) <= radius * radius;
        }

        public Translation2d getClosestPositionOnBorder(Translation2d position) {
            if (position.getX() == centerPosition.getX()) {
                return position.plus(new Translation2d(EPSILON, 0));
            }
            double slope = ((centerPosition.getY() - position.getY()) / centerPosition.getX() - position.getX());
            double angle = Math.atan(slope);
            double x = radius * Math.cos(angle) + centerPosition.getX();
            double y = radius * Math.sin(angle) + centerPosition.getY();
            return new Translation2d(x, y);
        }
    }
}
