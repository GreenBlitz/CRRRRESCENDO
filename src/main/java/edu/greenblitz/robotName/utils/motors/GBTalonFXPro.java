package edu.greenblitz.robotName.utils.motors;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.greenblitz.robotName.utils.systemCheck.IPingable;
import edu.greenblitz.robotName.utils.systemCheck.PingableManager;

public class GBTalonFXPro extends TalonFX implements IPingable {
	
	public GBTalonFXPro(int deviceId) {
		super(deviceId);
		PingableManager.getInstance().add(this);
	}
	
	public GBTalonFXPro(int deviceId, String canbus) {
		super(deviceId, canbus);
		PingableManager.getInstance().add(this, true);
	}
	
	public StatusCode setControl(ControlRequest control) {
		return super.setControl(control);
	}
	
	public StatusCode applyConfiguration(TalonFXConfiguration configuration) {
		return super.getConfigurator().apply(configuration);
	}

	@Override
	public int getDeviceID() {
		try {
			return super.getDeviceID();
		}
		catch (Exception e) {
			return -1;
		}
	}

	@Override
	public boolean isConnected() {
		return getDeviceID() >= 0;
	}

	@Override
	public String deviceName() {
		return "talon " + getDeviceID();
	}
}
