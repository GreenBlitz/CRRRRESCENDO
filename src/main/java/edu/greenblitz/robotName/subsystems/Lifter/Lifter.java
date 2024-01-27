package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.Lifter.NeoLifter.NeoLifterConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;

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
		lifter.updateInputs(lifterInputs);
		System.out.println(lifterInputs.position+","+ lifterInputs.velocity+","+ lifterInputs.appliedOutput);
	}
	
	public void goToPosition(double targetPosition) {
		lifter.goToPosition(targetPosition);
	}
	
	public void setPower(double power) {
		lifter.setPower(power);
	}
	
	public void setVoltage(double voltage) {
		lifter.setVoltage(voltage);
	}
	
	public void resetEncoder(double position) {
		lifter.resetEncoder(position);
	}
	
	public void resetEncoder() {
		resetEncoder(NeoLifterConstants.ENCODER_POSE_WHEN_RESET);
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
	
	public boolean isMotorAtPosition(double targetPosition) {
		return Math.abs(targetPosition - lifterInputs.position) < NeoLifterConstants.TOLERANCE;
	}
	
	public double getPosition(){
		return lifterInputs.position;
	}
}