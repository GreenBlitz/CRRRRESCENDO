package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;

public interface ILifter {
	void setPower(double power);
	void setVoltage(double voltage);
	void resetEncoder(double position);
	void stopMotor();
	void setIdleMode(CANSparkMax.IdleMode idleMode);
	void goToPosition(double position);
	void updateInputs(LifterInputsAutoLogged inputs);
}

