package edu.greenblitz.robotName.subsystems.shooter.FlyWheel;



public interface IFlyWheel {
    void setPower(double rightPower, double leftPower);

    void setVoltage(double rightVoltage, double leftVoltage);

    void setVelocity(double rightVelocity, double leftVelocity);

    void updateInputs(FlyWheelInputsAutoLogged inputs);
}
