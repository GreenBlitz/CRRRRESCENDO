package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

public class NeoFunnel implements IFunnel{
	
	private CANSparkMax motor;
	
	public NeoFunnel() {
		motor = new CANSparkMax(FunnelConstants.FUNNEL_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
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
		funnelInputs.velocity = motor.getEncoder().getVelocity();
	}
}
