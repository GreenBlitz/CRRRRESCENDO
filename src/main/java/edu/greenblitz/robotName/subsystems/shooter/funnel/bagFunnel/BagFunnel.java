package edu.greenblitz.robotName.subsystems.shooter.funnel.bagFunnel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.funnel.IFunnel;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj.DigitalInput;

public class BagFunnel implements IFunnel {
	
	private TalonSRX motor;
	
	private Debouncer debouncer;
	
	private DigitalInput beamBreaker;
	
	public BagFunnel() {
		motor = new TalonSRX(BagFunnelConstants.MOTOR_ID);
		
		debouncer = new Debouncer(BagFunnelConstants.DEBOUNCE_TIME_FOR_LIMIT_SWITCH_IN_SECONDS);
		beamBreaker = new DigitalInput(BagFunnelConstants.BEAM_BREAKER_CHANNEL);
	}
	
	@Override
	public void setPower(double power) {
		motor.set(ControlMode.PercentOutput, power);
	}
	
	@Override
	public void setVoltage(double voltage) {
	
	}
	
	@Override
	public void setVelocity(double velocity) {
	
	}
	
	
	@Override
	public void updateInputs(FunnelInputsAutoLogged funnelInputs) {
		funnelInputs.appliedOutput = motor.getMotorOutputVoltage();
		funnelInputs.appliedOutput = motor.getMotorOutputVoltage();
		funnelInputs.isObjectIn = debouncer.calculate(beamBreaker.get());
	}
}