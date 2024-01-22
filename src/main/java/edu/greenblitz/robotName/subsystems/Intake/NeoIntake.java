package edu.greenblitz.robotName.subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;

public class NeoIntake implements IIntake {
	private CANSparkMax motor;
	private static DigitalInput beamBreaker;
	
	public NeoIntake() {
		motor = new CANSparkMax(IntakeConstants.INTAKE_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
		beamBreaker = new DigitalInput(IntakeConstants.BREAM_BREAKER_CHANNEL);
	}
	
	@Override
	public void setPower(double power) {
		motor.set(power);
	}
	
	@Override
	public void setVoltage(double voltage) {
		motor.setVoltage(voltage);
	}
	
	@Override
	public void updateInputs(IntakeInputsAutoLogged intakeInputs) {
		intakeInputs.outputCurrent = motor.getOutputCurrent();
		intakeInputs.appliedOutput = motor.getAppliedOutput();
		intakeInputs.velocity = motor.getEncoder().getVelocity();
		intakeInputs.beamBreakerValue = beamBreaker.get();
	}
	
}
