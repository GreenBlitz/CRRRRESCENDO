package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import java.util.function.Supplier;

public class ShootAutomatically extends SequentialCommandGroup {
    public ShootAutomatically(){
        super(
                new RotateToAngle((Supplier<Rotation2d>) SwerveChassis.getInstance().getChassisAngleForAutoShooting(SwerveChassis.getInstance().getRobotPose())),
                new MovePivotToAngle(Pivot.getInstance().getChassisAngleForAutoShooting(SwerveChassis.getInstance().getRobotPose())),
                new Shoot()
        );
    }
}
