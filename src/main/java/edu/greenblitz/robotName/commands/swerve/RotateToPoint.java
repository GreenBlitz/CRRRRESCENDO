package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

import java.util.function.Supplier;

public class RotateToPoint extends RotateToAngleSupplier{
    public RotateToPoint(Supplier<Translation2d> targetPointSupplier) {
        super(
                () -> (targetPointSupplier.get()).minus(SwerveChassis.getInstance().getRobotPose().getTranslation()).getAngle(),
                () -> getFeedForward(targetPointSupplier.get())
        );
    }

    public static double getFeedForward(Translation2d targetPoint){
        targetPoint = targetPoint.minus(SwerveChassis.getInstance().getRobotPose().getTranslation());
        double vy = SwerveChassis.getInstance().getChassisSpeeds().vyMetersPerSecond;
        double vx = SwerveChassis.getInstance().getChassisSpeeds().vxMetersPerSecond;
        double dx = targetPoint.getX();
        double dy = targetPoint.getY();
        if (dx == 0 || dy == 0) {
            return 0;
        }
        return -((vy * dx + vx * dy) / (dx * dx + dy * dy));
    }
}
