package edu.greenblitz.robotName.subsystems.arm.roller;


import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelInputsAutoLogged;
import edu.wpi.first.math.geometry.Rotation2d;

public interface IRoller {

	void setPower(double power);

	void setVoltage(double voltage);

	void resetEncoder(Rotation2d position);

	void moveToPosition (Rotation2d position);

	void updateInputs(RollerInputsAutoLogged rollerInputs);

}
