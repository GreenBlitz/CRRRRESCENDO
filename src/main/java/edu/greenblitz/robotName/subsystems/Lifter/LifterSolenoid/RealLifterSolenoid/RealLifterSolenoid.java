package edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.RealLifterSolenoid;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.ILifterSolenoid;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoidConstants;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoidInputsAutoLogged;

public class RealLifterSolenoid implements ILifterSolenoid {

	private TalonSRX solenoid;

	public RealLifterSolenoid(){
		solenoid = new TalonSRX(RealLifterSolenoidConstants.SOLENOID_ID);
	}

	@Override
	public void openSolenoid() {
		solenoid.set(TalonSRXControlMode.PercentOutput, LifterSolenoidConstants.POWER_TO_OPEN);
	}

	@Override
	public void closeSolenoid() {
		solenoid.set(TalonSRXControlMode.PercentOutput, LifterSolenoidConstants.POWER_TO_CLOSE);
	}

	@Override
	public void holdSolenoid() {
		solenoid.set(TalonSRXControlMode.PercentOutput, LifterSolenoidConstants.POWER_TO_HOLD);
	}

	@Override
	public void updateInputs(LifterSolenoidInputsAutoLogged inputs) {
		inputs.current = solenoid.getStatorCurrent();
		inputs.voltage = solenoid.getMotorOutputVoltage();
		inputs.isOpen = inputs.voltage > 0;
	}
}
