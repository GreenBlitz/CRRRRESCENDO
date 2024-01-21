package edu.greenblitz.robotName.subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

public class RealIntake implements IIntake {
	private CANSparkMax motor;
	
	public RealIntake() {
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
	public void updateInputs(IntakeInputsAutoLogged chassisIntakeInputs) {
		chassisIntakeInputs.outputCurrent = motor.getOutputCurrent();
		chassisIntakeInputs.appliedOutput = motor.getAppliedOutput();
		chassisIntakeInputs.velocity = motor.getEncoder().getVelocity();
	}
}
