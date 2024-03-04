package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class RotateToAngleSupplier implements DoubleSupplier {

	public Supplier<Rotation2d> angleSupplier;

	public DoubleSupplier feedForwardSupplier;

	public PIDController pidController;

	public RotateToAngleSupplier(Supplier<Rotation2d> targetAngle) {
		this(targetAngle, () -> 0);
	}

	public RotateToAngleSupplier(Supplier<Rotation2d> targetAngle, DoubleSupplier feedForwardSupplier) {
		this.angleSupplier = targetAngle;
		this.feedForwardSupplier = feedForwardSupplier;
		pidController = new PIDController(0.076, 0, 0);
		pidController.setTolerance(Units.degreesToRadians(2));
		pidController.enableContinuousInput(-Math.PI, Math.PI);
	}

	@Override
	public double getAsDouble() {
		pidController.setSetpoint(angleSupplier.get().getRadians());
		pidController.setTolerance(Units.degreesToRadians(10));
		double pidOutput = pidController.calculate(
				SwerveChassis.getInstance().getChassisAngle().getRadians()
		);
		return pidController.atSetpoint() ? 0 : pidOutput;
	}

	public boolean isAtSetpoint() {
		Rotation2d currentAngle = SwerveChassis.getInstance().getChassisAngle();
		Rotation2d targetAngle = angleSupplier.get();
		return (currentAngle.getRadians() - targetAngle.getRadians()) % (2 * Math.PI) < Units.degreesToRadians(5) ||
				(targetAngle.getRadians() - currentAngle.getRadians()) % (2 * Math.PI) < Units.degreesToRadians(5);
	}
}
