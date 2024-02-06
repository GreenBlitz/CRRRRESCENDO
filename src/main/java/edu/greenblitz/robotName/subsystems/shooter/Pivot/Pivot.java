package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.SimulationPivot.SimulationPivotConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.*;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants.TOLERANCE;

public class Pivot extends GBSubsystem {

	private static Pivot instance;

	private PivotInputsAutoLogged pivotInputs;

	private Rotation2d currentAngle;

	private IPivot pivot;

	private Pivot() {
		pivot = PivotFactory.create();
		pivotInputs = new PivotInputsAutoLogged();
		pivot.updateInputs(pivotInputs);
	}

	public static void init() {
		if (instance == null){
			instance = new Pivot();
		}
	}

	public static Pivot getInstance() {
		init();
		return instance;
	}

	@Override
	public void periodic() {
		super.periodic();

		pivot.updateInputs(pivotInputs);
		Logger.processInputs("Shooter/Pivot", pivotInputs);
		Logger.recordOutput("Shooter/Pivot", getSimulationPivotPosition3d());
	}

	public void setCurrentAngle(Rotation2d angle){
		currentAngle = angle;
	}

	public void setPower(double power) {
		pivot.setPower(power);
	}

	public void setMotorVoltage(double voltage) {
		pivot.setVoltage(voltage);
	}

	public void setIdleMode(NeutralModeValue idleMode) {
		pivot.setIdleMode(idleMode);
	}

	public void resetAngle(Rotation2d position) {
		pivot.resetAngle(position);
	}

	public void moveToAngle(Rotation2d targetAngle) {
		pivot.moveToAngle(targetAngle);
	}

	public void standInPlace() {
		pivot.standInPlace(currentAngle);
	}

	public double getVoltage() {
		return pivotInputs.appliedOutput * Battery.getInstance().getCurrentVoltage();
	}

	public double getVelocity() {
		return pivotInputs.velocity;
	}

	public Rotation2d getAngle() {
		return pivotInputs.position;
	}

	public boolean isAtAngle(Rotation2d angle) {
		return Math.abs(angle.getRadians() - getAngle().getRadians()) <= TOLERANCE.getRadians();
	}

	public Pose3d getSimulationPivotPosition3d() {
		return new Pose3d(
				PivotConstants.ROBOT_RELATIVE_PIVOT_POSITION,
				new Rotation3d(-getAngle().getRadians(), 0, 0)
		);
	}
}