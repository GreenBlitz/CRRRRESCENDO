package edu.greenblitz.robotName.subsystems.lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import org.littletonrobotics.junction.Logger;

public class Lifter extends GBSubsystem {
 
	private static Lifter instance;
	
    private ILifter lifter;
    
	private LifterInputsAutoLogged lifterInputs;
	
	private Lifter() {
		lifter = LifterFactory.create();
		lifterInputs = new LifterInputsAutoLogged();
		lifter.updateInputs(lifterInputs);
		setIdleMode(CANSparkMax.IdleMode.kBrake);
	}
	
	public static Lifter getInstance() {
		init();
		return instance;
	}
	
	public static void init() {
		if (instance == null) {
			instance = new Lifter();
		}
	}
	
	@Override
	public void periodic() {
		super.periodic();
		lifter.updateInputs(lifterInputs);
		
		Logger.processInputs("Lifter/Lifter", lifterInputs);
		Logger.recordOutput("Lifter/Lifter", getLifterPose3d());
	}
	
	public void goToPosition(Rotation2d targetPosition) {
		lifter.goToPosition(targetPosition);
	}
	
	public void setPower(double power) {
		lifter.setPower(power);
	}
	
	public void setVoltage(double voltage) {
		lifter.setVoltage(voltage);
	}
	
	public void resetEncoder(Rotation2d position) {
		lifter.resetEncoder(position);
	}
	
	public void resetEncoder() {
		resetEncoder(LifterConstants.ENCODER_POSITION_WHEN_RESET);
	}
	
	public void stopMotor() {
		lifter.stopMotor();
	}
	
	public void setIdleMode(CANSparkMax.IdleMode mode) {
		lifter.setIdleMode(mode);
	}
	
	public boolean isSwitchPressed() {
		return lifterInputs.isBackwardSwitchPressed;
	}
	
	public boolean isAtPosition(Rotation2d targetPosition) {
		return Math.abs(targetPosition.getRadians() - lifterInputs.position) < LifterConstants.TOLERANCE.getRadians();
	}
	
	public Rotation2d getPosition() {
		return Rotation2d.fromRadians(lifterInputs.position);
	}
	
	public Pose3d getLifterPose3d() {
		return new Pose3d(
				LifterConstants.ROBOT_RELATIVE_LIFTER_POSITION,
				new Rotation3d(
						-lifterInputs.position,
						0,
						0
				).plus(LifterConstants.ROBOT_RELATIVE_LIFTER_ROTATION)
		);
	}
}