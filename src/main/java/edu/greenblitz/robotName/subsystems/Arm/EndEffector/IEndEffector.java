package edu.greenblitz.robotName.subsystems.Arm.EndEffector;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkMax;

public interface IEndEffector {

    void setPower(double power);

    void setVoltage(double voltage);

    void setIdleMode(CANSparkMax.IdleMode idleMode);

    void resetAngle(double position);

    void moveToAngle(double goalAngle);

    void updateInputs(EndEffectorInputsAutoLogged inputs);
}
