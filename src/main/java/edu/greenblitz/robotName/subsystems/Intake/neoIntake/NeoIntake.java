package edu.greenblitz.robotName.subsystems.Intake.neoIntake;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.SparkMaxLimitSwitch;
import edu.greenblitz.robotName.subsystems.Intake.IIntake;
import edu.greenblitz.robotName.subsystems.Intake.IntakeConstants;
import edu.greenblitz.robotName.subsystems.Intake.IntakeInputsAutoLogged;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.filter.Debouncer;

import static edu.greenblitz.robotName.subsystems.Intake.neoIntake.NeoIntakeConstants.*;

public class NeoIntake implements IIntake {

	private GBSparkMax motor;

	private Debouncer entranceBeamBreaker;

	private Debouncer exitBeamBreaker;


	public NeoIntake() {
		motor = new GBSparkMax(NeoIntakeConstants.INTAKE_ID, CANSparkLowLevel.MotorType.kBrushless);
		motor.config(NeoIntakeConstants.INTAKE_CONFIG_OBJECT);

		entranceBeamBreaker = new Debouncer(NeoIntakeConstants.ENTRANCE_DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
		exitBeamBreaker = new Debouncer(NeoIntakeConstants.EXIT_DEBOUNCE_TIME_FOR_LIMIT_SWITCH);

		motor.getReverseLimitSwitch(NeoIntakeConstants.ENTRANCE_BEAM_BREAKER_TYPE).enableLimitSwitch(NeoIntakeConstants.IS_ENTRANCE_BEAM_BREAKER_ACTIVE);
		motor.getForwardLimitSwitch(NeoIntakeConstants.EXIT_BEAM_BREAKER_TYPE).enableLimitSwitch(NeoIntakeConstants.IS_EXIT_BEAM_BREAKER_ACTIVE);
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
		intakeInputs.entranceBeamBreakerValue = entranceBeamBreaker.calculate(motor.getReverseLimitSwitch(NeoIntakeConstants.ENTRANCE_BEAM_BREAKER_TYPE).isPressed());
		intakeInputs.exitBeamBreakerValue = exitBeamBreaker.calculate(motor.getForwardLimitSwitch(NeoIntakeConstants.EXIT_BEAM_BREAKER_TYPE).isPressed());
	}
}