package edu.greenblitz.robotName.subsystems.swerve.chassis;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class GetRotationSupplier implements DoubleSupplier {

	public Supplier<Rotation2d> angleSupplier;

	public DoubleSupplier feedForwardSupplier;

	public PIDController pidController = ChassisConstants.ROTATION_PID_CONTROLLER_RADIANS;

	public GetRotationSupplier(Supplier<Rotation2d> targetAngle) {
		this(targetAngle, () -> 0);
	}

	public GetRotationSupplier(Supplier<Rotation2d> targetAngle, DoubleSupplier feedForwardSupplier) {
		this.angleSupplier = targetAngle;
		this.feedForwardSupplier = feedForwardSupplier;
	}

	@Override
	public double getAsDouble() {
		pidController.setSetpoint(angleSupplier.get().getRadians());
		double pidOutput = pidController.calculate(
				SwerveChassis.getInstance().getChassisAngle().getRadians()
		);
		return pidController.atSetpoint() ? 0 : pidOutput;
	}
}
