package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;

public interface ILifter {

	void setPower(double power);

	void setVoltage(double voltage);

	void resetEncoder(Rotation2d position);

	void stopMotor();

	void setIdleMode(CANSparkMax.IdleMode idleMode);

	void goToPosition(Rotation2d position);

	void closeSolenoid();

	void openSolenoid();

	void holdSolenoid();

	void updateInputs(LifterInputsAutoLogged inputs);
}

