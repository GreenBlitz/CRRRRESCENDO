package edu.greenblitz.robotName.utils.motors;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.greenblitz.robotName.utils.systemCheck.IPingable;

public class GBTalonFXPro extends TalonFX implements IPingable {
	
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

	@Override
	public boolean isConnected() {
		try {
			getDeviceID();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	public String deviceName() {
		return "talon " + getDeviceID();
	}
}
