package edu.greenblitz.robotName.subsystems.Shooter.Funnel;

public interface IFunnel {
	
	void setPower(double power);
	
	void setVoltage(double voltage);
	
	void updateInputs(FunnelInputsAutoLogged funnelInputs);
}
