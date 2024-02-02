package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.*;
import edu.wpi.first.math.util.Units;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.shooter.Pivot.FalconPivot.FalconPivotConstants.SIMPLE_MOTOR_FF;
import static edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants.TOLERANCE;

public class Pivot extends GBSubsystem {

	private static Pivot instance;
	private PivotInputsAutoLogged pivotInputs;
	Pose2d robotPose;
	private IPivot pivot;

	private Pivot() {
		pivot = PivotFactory.create();
		pivotInputs = new PivotInputsAutoLogged();
		robotPose = SwerveChassis.getInstance().getRobotPose();
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
		Logger.recordOutput("Shooter/Pivot", getPivotPose3d());
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
		pivot.setVoltage(getStaticFeedForward());
	}
	public double getStaticFeedForward() {
		return Robot.isSimulation() ? 0 : SIMPLE_MOTOR_FF.calculate(0);
	}

	public double getDynamicFeedForward(double velocity) {
		return SIMPLE_MOTOR_FF.calculate(velocity);
	}

	public double getVoltage() {
		return pivotInputs.appliedOutput * Battery.getInstance().getCurrentVoltage();
	}

	public double getVelocity() {
		return pivotInputs.velocity;
	}

	public Rotation2d getAngle() {
		return Rotation2d.fromRadians(pivotInputs.position);
	}
	public boolean isAtAngle(Rotation2d angle) {
		return Math.abs(angle.getRadians() - getAngle().getRadians()) <= TOLERANCE;
	}
	public Pose3d getPivotPose3d() {
		return new Pose3d(
				PivotConstants.ROBOT_RELATIVE_PIVOT_POSITION,
				new Rotation3d(getAngle().getRadians(), 0, 0)
		);
	}
}