package edu.greenblitz.robotName.subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.filter.Debouncer;

public class NeoIntake implements IIntake {
	private CANSparkMax motor;
	private static Debouncer entranceBeamBreaker;
	private static Debouncer exitBeamBreaker;

	public NeoIntake() {
		motor = new CANSparkMax(IntakeConstants.INTAKE_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
		entranceBeamBreaker = new Debouncer(IntakeConstants.ENTRANCE_DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
		exitBeamBreaker = new Debouncer(IntakeConstants.EXIT_DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
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
		intakeInputs.entranceBeamBreakerValue = entranceBeamBreaker.calculate(motor.getReverseLimitSwitch(IntakeConstants.EXIT_BEAM_BREAKER_TYPE).isPressed());
		intakeInputs.exitBeamBreakerValue = exitBeamBreaker.calculate(motor.getReverseLimitSwitch(IntakeConstants.ENTRANCE_BEAM_BREAKER_TYPE).isPressed());
	}
}