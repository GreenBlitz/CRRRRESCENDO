package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;

public interface ILifter {
    public void setPower(double power);

    public void setVoltage(double voltage);

    public void resetEncoder(double position);

    public void stopMotor();

    public void setIdleMode(CANSparkMax.IdleMode idleMode);

    public void updateInputs(LifterInputsAutoLogged inputs);
}

