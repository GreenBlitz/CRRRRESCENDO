package edu.greenblitz.robotName.subsystems.shooter.FlyWheel;



public interface IFlyWheel {
    void setPower(double power);

    void setVoltage(double voltage);

    void setVelocity(double velocity);

    void updateInputs(FlyWheelInputsAutoLogged inputs);
}