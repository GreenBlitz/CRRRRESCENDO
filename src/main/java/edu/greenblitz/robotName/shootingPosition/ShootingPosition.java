package edu.greenblitz.robotName.shootingPosition;

import com.sun.source.tree.ReturnTree;
import edu.wpi.first.math.geometry.Pose2d;

import java.awt.geom.Ellipse2D;
import java.security.spec.EllipticCurve;

import static edu.greenblitz.robotName.shootingPosition.ShootingPositionConstants.*;

public class ShootingPosition {

    public boolean isInRange(Pose2d pose) {
        return CURVE.contains(pose.getX(), pose.getY());
    }

    public Pose2d getClosestToCurvePose(Pose2d pose) {

    }

}
