package edu.greenblitz.robotName.subsystems.Intake;

import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import org.littletonrobotics.junction.Logger;

public class Intake extends GBSubsystem {
	
	private static Intake instance;
	private static IntakeInputsAutoLogged intakeInputs;
	private static IIntake intake;
	
	private Intake() {
		intake = IntakeFactory.create();
		intakeInputs = new IntakeInputsAutoLogged();
		intake.updateInputs(intakeInputs);
	}

	public static void init(){
		if (instance == null)
			instance = new Intake();
	}
	
	public static Intake getInstance() {
		init();
		return instance;
	}
	
	public void periodic() {
		intake.updateInputs(intakeInputs);
		Logger.processInputs("intake", intakeInputs);
	}
	
	public static void setPower(double power) {
		intake.setPower(power);
	}
	
	public static void setVoltage(double voltage) {
		intake.setVoltage(voltage);
	}
	
	public static double getVoltage() {
		return intakeInputs.appliedOutput;
	}
	
	public static double getVelocity() {
		return intakeInputs.velocity;
	}
	
	public static void stop() {
		setPower(0);
	}
	
	public boolean getBeamBreakerValue(){
		return intakeInputs.beamBreakerValue;
	}

	
	
}
