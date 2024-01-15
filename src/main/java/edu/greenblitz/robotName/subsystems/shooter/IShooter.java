package edu.greenblitz.robotName.subsystems.shooter;

public interface IShooter {
    void updateInputs(ShooterInputs inputs);
    void setPower(double power);
    void setVoltage(double voltage);
}
