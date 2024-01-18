package edu.greenblitz.robotName.subsystems.shooter;

import com.revrobotics.CANSparkMax;

public interface IShooter {
    void setPower(double power);

    void setVoltage(double voltage);

    void setVelocity(double velocity);

    void updateInputs(ShooterInputsAutoLogged inputs);
}
