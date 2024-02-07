package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import edu.wpi.first.math.geometry.Rotation2d;

public interface IFunnel {
	
	void setPower(double power);
	
	void setVoltage(double voltage);
	
	void updateInputs(FunnelInputsAutoLogged funnelInputs);

	void resetEncoder(Rotation2d position);
	void moveToPosition (Rotation2d position);
}
