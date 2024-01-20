package edu.greenblitz.robotName.subsystems.shooter.FlyWheel;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheelInputsAutoLogged;

public interface IFlyWheel {
    void setPower(double power);

    void setVoltage(double voltage);

    void setVelocity(double velocity);

    void updateInputs(FlyWheelInputsAutoLogged inputs);
}
