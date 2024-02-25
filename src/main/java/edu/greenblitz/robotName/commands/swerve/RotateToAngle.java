package edu.greenblitz.robotName.commands.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
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
    }

    @Override
    public void execute() {
        swerveChassis.rotateToAngle(angleSetPoint);
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