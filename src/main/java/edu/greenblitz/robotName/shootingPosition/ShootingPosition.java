package edu.greenblitz.robotName.shootingPosition;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.constraint.EllipticalRegionConstraint;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.security.spec.EllipticCurve;

import static edu.greenblitz.robotName.shootingPosition.ShootingPositionConstants.*;

public class ShootingPosition {
    private EllipticCurve curve;

    public boolean isInRange(Pose2d pose) {
        curve = new EllipticCurve(

        );
    }

    public Pose2d getAnglePose(Pose2d pose) {

    }

}
