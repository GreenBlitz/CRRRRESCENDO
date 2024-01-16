package edu.greenblitz.robotName.subsystems.shooter;

public interface IFlyWheel {
    void setPower(double power);
    void setVoltage(double voltage);
    void setVelocity (double velocity);

    void updateInputs(ShooterInputsAutoLogged inputs);
}
