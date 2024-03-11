package edu.greenblitz.robotName.subsystems.climbing.solenoid.talonSRXSolenoid;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.greenblitz.robotName.subsystems.climbing.solenoid.ISolenoid;
import edu.greenblitz.robotName.subsystems.climbing.solenoid.SolenoidConstants;
import edu.greenblitz.robotName.subsystems.climbing.solenoid.SolenoidInputsAutoLogged;

public class TalonSRXSolenoid implements ISolenoid {

	private TalonSRX solenoid;

	public TalonSRXSolenoid(){
		solenoid = new TalonSRX(TalonSRXSolenoidConstants.SOLENOID_ID);
	}

	@Override
	public void openSolenoid() {
		setPowerToSolenoid(SolenoidConstants.POWER_TO_OPEN_SOLENOID);
	}

	@Override
	public void closeSolenoid() {
		setPowerToSolenoid(SolenoidConstants.POWER_TO_CLOSE_SOLENOID);
	}

	@Override
	public void holdSolenoid() {
		setPowerToSolenoid(SolenoidConstants.POWER_TO_HOLD_SOLENOID);
	}

	@Override
	public void setPowerToSolenoid(double powerSolenoid) {
		solenoid.set(TalonSRXControlMode.PercentOutput, powerSolenoid);
	}

	@Override
	public void updateInputs(SolenoidInputsAutoLogged inputs) {
		inputs.currentSolenoid = solenoid.getStatorCurrent();
		inputs.voltageSolenoid = solenoid.getMotorOutputVoltage();
		inputs.isOpenSolenoid = inputs.voltageSolenoid > 0;
	}



}
