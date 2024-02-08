package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;

import java.util.function.Supplier;

public class RotateToAngle extends SwerveCommand {

    private Supplier<Rotation2d> angleSetPoint;

    public RotateToAngle(Rotation2d angleSetPoint) {
        super();
        this.angleSetPoint = () -> angleSetPoint;
    }

    public RotateToAngle(Supplier<Rotation2d> angleSetPoint) {
        super();
        this.angleSetPoint = angleSetPoint;
    }

    @Override
    public void initialize() {
        ChassisConstants.ROTATION_PID_CONTROLLER.setSetpoint(angleSetPoint.get().getRadians());
    }

    @Override
    public void execute() {
        SwerveChassis.getInstance().rotateToAngle();
    }

    @Override
    public boolean isFinished() {
        return SwerveChassis.getInstance().isAtAngle(angleSetPoint.get());
    }

    @Override
    public void end(boolean interrupted) {
        SwerveChassis.getInstance().stop();
    }
}