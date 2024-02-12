package edu.greenblitz.robotName.subsystems.intake;

import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

public class Intake extends GBSubsystem {
	
	private static Intake instance;
 
	private IntakeInputsAutoLogged intakeInputs;
 
	private IIntake intake;
	
	private Intake() {
		intake = IntakeFactory.create();
		intakeInputs = new IntakeInputsAutoLogged();
		intake.updateInputs(intakeInputs);
	}
	
	public static void init() {
		if (instance == null)
			instance = new Intake();
	}
	
	public static Intake getInstance() {
		init();
		return instance;
	}
	
	public void setPower(double power) {
		intake.setPower(power);
	}
	
	public double getVoltage() {
		return intakeInputs.appliedOutput;
	}
	
	public void setVoltage(double voltage) {
		intake.setVoltage(voltage);
	}
	
	public double getVelocity() {
		return intakeInputs.velocity;
	}
	
	public void stop() {
		setPower(0);
	}
	
	public void rollIn() {
		setPower(IntakeConstants.POWER_TO_RUN);
	}
	
	public void rollOut() {
		setPower(IntakeConstants.POWER_TO_REVERSE_RUN);
	}
	
	public void periodic() {
		intake.updateInputs(intakeInputs);
		Logger.processInputs("intake", intakeInputs);
	}
	
	public boolean getEntranceBeamBreakerValue() {
		return intakeInputs.entranceBeamBreakerValue;
	}
	
	public boolean getExitBeamBreakerValue() {
		return intakeInputs.exitBeamBreakerValue;
	}
}