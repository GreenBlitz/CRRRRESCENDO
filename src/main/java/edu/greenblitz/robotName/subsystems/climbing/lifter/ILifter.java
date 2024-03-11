package edu.greenblitz.robotName.subsystems.climbing.lifter;

import com.revrobotics.CANSparkMax;

public interface ILifter {

    void setPower(double power);

    void setVoltage(double voltage);

    void resetEncoder(double position);

    void stop();

    void setIdleMode(CANSparkMax.IdleMode idleMode);

    void goToPosition(double position);

    void updateInputs(LifterInputsAutoLogged lifterInputsAutoLogged);
}