package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import org.littletonrobotics.junction.AutoLog;

public interface IFunnel {
	
	void setPower(double power);
	
	void setVoltage(double voltage);
	
	void updateInputs(FunnelInputsAutoLogged chassisIntakeInputs);
}
