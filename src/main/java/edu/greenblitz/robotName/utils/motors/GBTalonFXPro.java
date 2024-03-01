package edu.greenblitz.robotName.utils.motors;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.greenblitz.robotName.utils.systemCheck.IPingable;
import edu.greenblitz.robotName.utils.systemCheck.PingableManager;

public class GBTalonFXPro extends TalonFX implements IPingable {

	private int deviceId;
	
	public GBTalonFXPro(int deviceId) {
		super(deviceId);
		PingableManager.getInstance().add(this);
		this.deviceId = deviceId;
	}
	
	public GBTalonFXPro(int deviceId, String canbus) {
		super(deviceId, canbus);
		PingableManager.getInstance().add(this, true);
		this.deviceId = deviceId;
	}

	public double getLatencyCompensatedValue(StatusSignal<Double> value, StatusSignal<Double> valueSlope){
		return BaseStatusSignal.getLatencyCompensatedValue(value, valueSlope);
	}

	public StatusCode setControl(ControlRequest control) {
		return super.setControl(control);
	}
	
	public StatusCode applyConfiguration(TalonFXConfiguration configuration) {
		return super.getConfigurator().apply(configuration);
	}

	@Override
	public boolean isConnected() {
		return isAlive();
	}

	@Override
	public String deviceName() {
		return "talon " + deviceId;
	}
}
