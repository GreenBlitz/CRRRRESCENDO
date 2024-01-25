package edu.greenblitz.robotName.subsystems.shooter.Funnel;

public interface IFunnel {
	
	void setPower(double power);
	
	void setVoltage(double voltage);
	
	void updateInputs(FunnelInputsAutoLogged funnelInputs);
}
