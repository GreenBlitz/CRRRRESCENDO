package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.Logger;

import java.util.function.Supplier;

public class RotateToAngle extends SwerveCommand {

    private Supplier<Rotation2d> angleSetPointSupplier;

    private Rotation2d angleSetPoint;

    public RotateToAngle(Rotation2d angleSetPoint) {
        super();
        this.angleSetPointSupplier = () -> angleSetPoint;
    }

    public RotateToAngle(Supplier<Rotation2d> angleSetPointSupplier) {
        super();
        this.angleSetPointSupplier = angleSetPointSupplier;
    }

    @Override
    public void initialize() {
        angleSetPoint = angleSetPointSupplier.get();
        swerveChassis.setGoalAngle(angleSetPoint);
    }

    @Override
    public void execute() {
        swerveChassis.rotateToAngle();
    }

    @Override
    public boolean isFinished() {
        return swerveChassis.isAtAngle(angleSetPoint);
    }

    @Override
    public void end(boolean interrupted) {
        swerveChassis.stop();
    }
}