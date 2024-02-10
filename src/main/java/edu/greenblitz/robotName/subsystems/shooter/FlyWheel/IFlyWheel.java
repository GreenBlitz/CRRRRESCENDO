package edu.greenblitz.robotName.subsystems.shooter.FlyWheel;

public interface IFlyWheel {

    void setPower(double leftPower, double rightPower);

    void setVoltage(double leftVoltage, double rightVoltage);

    void setVelocity(double leftVelocity, double rightVelocity);

    void updateInputs(FlyWheelInputsAutoLogged inputs);
}
