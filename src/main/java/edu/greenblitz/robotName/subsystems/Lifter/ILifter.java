package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;

public interface ILifter {
    public void setPower(double power);
    public void setVoltage(double voltage);
    public void resetEncoderTo(double position);
    public boolean isMotorInPosition(double position);
    public void updateInputs(LifterInputs lastInputs);
    public void stopMotor();
    public void setIdleMode(CANSparkMax.IdleMode mode);
    public double getPosition();
    public boolean isSwitchPressed();
}

