package edu.greenblitz.robotName.shootingPosition;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.constraint.EllipticalRegionConstraint;
import edu.wpi.first.math.trajectory.constraint.TrajectoryConstraint;

import java.awt.geom.Ellipse2D;
import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.EllipticCurve;

public class ShootingPositionConstants {
    public static final int X = 69;
    public static final int Y = 69;
    public static final int WIDTH = 69;
    public static final int HIGHT = 69;
    public static final Ellipse2D CURVE = new Ellipse2D.Double(X, Y, WIDTH, HIGHT);
}
