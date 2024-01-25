package edu.greenblitz.robotName.subsystems.shooter.Funnel.NeoFunnel;

import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.IFunnel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj.DigitalInput;

import static edu.greenblitz.robotName.subsystems.shooter.Funnel.NeoFunnel.NeoFunnelConstants.*;

public class NeoFunnel implements IFunnel {
	
	private GBSparkMax motor;

	private Debouncer debouncer;
	
	private DigitalInput beamBreaker;

	public NeoFunnel() {
		motor = new GBSparkMax(FUNNEL_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
		motor.config(FUNNEL_CONFIG_OBJECT);
		beamBreaker = new DigitalInput(BEAM_BREAKER_CHANNEL);
		debouncer = new Debouncer(DEBOUNCE_TIME_FOR_LIMIT_SWITCH);
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
	public void updateInputs(FunnelInputsAutoLogged funnelInputs) {
		funnelInputs.outputCurrent = motor.getOutputCurrent();
		funnelInputs.appliedOutput = motor.getAppliedOutput();
		funnelInputs.temperature = motor.getMotorTemperature();
		funnelInputs.isObjectIn = debouncer.calculate(beamBreaker.get());
	}
}
