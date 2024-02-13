package edu.greenblitz.robotName.subsystems.shooter.funnel;

public interface IFunnel {
	
	void setPower(double power);
	
	void updateInputs(FunnelInputsAutoLogged funnelInputs);
	
}