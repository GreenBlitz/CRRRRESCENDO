package edu.greenblitz.robotName.subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

public class NeoIntake implements IIntake {
	private CANSparkMax motor;
	
	public NeoIntake() {
		motor = new CANSparkMax(IntakeConstants.INTAKE_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
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
	}
}
