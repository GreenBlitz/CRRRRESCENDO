package edu.greenblitz.robotName.utils.motors;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.hardware.TalonFX;

public class GBTalonFXPro extends TalonFX {
	

	public GBTalonFXPro(int deviceId) {
		super(deviceId);
	}

	public GBTalonFXPro(int deviceId, String canbus) {
		super(deviceId, canbus);
	}

	public StatusCode setControl(ControlRequest control) {
		return super.setControl(control);
	}
	public StatusCode applyConfiguration(TalonFXConfiguration configuration) {
		return super.getConfigurator().apply(configuration);
	}


}
