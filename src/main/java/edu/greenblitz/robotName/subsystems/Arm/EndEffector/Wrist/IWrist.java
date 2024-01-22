package edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist;

import com.revrobotics.CANSparkMax;

public interface IWrist {

    void setPower(double power);

    void setVoltage(double voltage);

    void setIdleMode(CANSparkMax.IdleMode idleMode);

    void resetAngle(double position);

    void moveToAngle(double goalAngle);

    void updateInputs(WristInputsAutoLogged inputs);
}
