package edu.greenblitz.robotName.subsystems.shooter.pivot;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import org.littletonrobotics.junction.Logger;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;

public class Pivot extends GBSubsystem {
	
	private static Pivot instance;
	
	private PivotInputsAutoLogged pivotInputs;
	
	private Rotation2d currentAngle;
	
	private IPivot pivot;
	
	private Pivot() {
		pivot = PivotFactory.create();
		pivotInputs = new PivotInputsAutoLogged();
		pivot.updateInputs(pivotInputs);
		currentAngle = getAngle();
	}
	
	public static void init() {
		if (instance == null) {
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
		Logger.recordOutput("Shooter", getSimulationPivotPosition3d());
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
	
	public void setCurrentAngle() {
		currentAngle = getAngle();
	}
	
	public void standInPlace() {
		pivot.moveToAngle(currentAngle);
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
		return Math.abs(angle.getRadians() - getAngle().getRadians()) <= PivotConstants.TOLERANCE.getRadians();
	}
	public Rotation2d getAbsolutePosition (){
		return pivotInputs.absoluteEncoderPosition;
	}
	
	public Pose3d getSimulationPivotPosition3d() {
		return new Pose3d(
				PivotConstants.ROBOT_RELATIVE_PIVOT_POSITION,
				new Rotation3d(0, -getAngle().getRadians(), 0)
		);
	}
}