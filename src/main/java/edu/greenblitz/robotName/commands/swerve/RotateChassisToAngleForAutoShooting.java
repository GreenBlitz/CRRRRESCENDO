package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;

import java.util.function.Supplier;

public class RotateChassisToAngleForAutoShooting extends RotateToAngle{
    public RotateChassisToAngleForAutoShooting() {
        super((Supplier<Rotation2d>) SwerveChassis.getInstance().getChassisAngleForAutoShooting(SwerveChassis.getInstance().getRobotPose()));
    }
}
