package edu.greenblitz.robotName.subsystems.ChassisIntake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

public class RealChassisIntake implements IChassisIntake {
	private CANSparkMax motor;
	
	public RealChassisIntake() {
		motor = new CANSparkMax(ChassisIntakeConstants.INTAKE_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
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
	public void updateInputs(ChassisIntakeInputsAutoLogged chassisIntakeInputs) {
		chassisIntakeInputs.outputCurrent = motor.getOutputCurrent();
		chassisIntakeInputs.appliedOutput = motor.getAppliedOutput();
		chassisIntakeInputs.velocity = motor.getEncoder().getVelocity();
	}
}
