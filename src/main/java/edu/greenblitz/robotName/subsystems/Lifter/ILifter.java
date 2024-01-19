package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;

public interface ILifter {

    public void setPowerForMotor1(double power);

    public void setPowerForMotor2(double power);

    public void setVoltageForMotor1(double voltage);

    public void setVoltageForMotor2(double voltage);

    public void setPositionForMotor1(double position);

    public void setPositionForMotor2(double position);

    public boolean isMotor1InPosition(double position);

    public boolean isMotor2InPosition(double position);

    public void updateInputs(LifterInputs lastInputs);

    public void stopMotor1();

    public void stopMotor2();

    public void setIdleModeForMotor1(CANSparkMax.IdleMode mode);
    public void setIdleModeForMotor2(CANSparkMax.IdleMode mode);
    public SparkMaxPIDController getMotor1PID();
    public SparkMaxPIDController getMotor2PID();


}

