package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;

public interface ILifter {
	void setPower(double power);
	void setVoltage(double voltage);
	void resetEncoder(double position);
	void stopMotor();
	void setIdleMode(CANSparkMax.IdleMode idleMode);
	void goToPosition(Rotation2d position);
	void updateInputs(LifterInputsAutoLogged inputs);
}

