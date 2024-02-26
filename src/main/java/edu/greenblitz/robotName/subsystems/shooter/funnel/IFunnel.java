package edu.greenblitz.robotName.subsystems.shooter.funnel;

public interface IFunnel {
	
	void setPower(double power);
	
	void setVoltage(double voltage);
	
	void setVelocity(double velocity);
	
	void updateInputs(FunnelInputsAutoLogged funnelInputs);
	
}