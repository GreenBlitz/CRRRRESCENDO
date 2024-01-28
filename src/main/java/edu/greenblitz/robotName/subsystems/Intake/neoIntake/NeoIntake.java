package edu.greenblitz.robotName.subsystems.Intake.neoIntake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.subsystems.Intake.IIntake;
import edu.greenblitz.robotName.subsystems.Intake.IntakeConstants;
import edu.greenblitz.robotName.subsystems.Intake.IntakeInputsAutoLogged;
import edu.wpi.first.math.filter.Debouncer;

public class NeoIntake implements IIntake {
	private CANSparkMax motor;
	private Debouncer entranceBeamBreaker;
	private Debouncer exitBeamBreaker;

	public NeoIntake() {
		motor = new CANSparkMax(NeoIntakeConstants.INTAKE_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
		entranceBeamBreaker = new Debouncer(NeoIntakeConstants.ENTRANCE_DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
		exitBeamBreaker = new Debouncer(NeoIntakeConstants.EXIT_DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
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
		intakeInputs.entranceBeamBreakerValue = entranceBeamBreaker.calculate(motor.getReverseLimitSwitch(NeoIntakeConstants.EXIT_BEAM_BREAKER_TYPE).isPressed());
		intakeInputs.exitBeamBreakerValue = exitBeamBreaker.calculate(motor.getReverseLimitSwitch(NeoIntakeConstants.ENTRANCE_BEAM_BREAKER_TYPE).isPressed());
	}
}