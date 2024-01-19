package edu.greenblitz.robotName.subsystems.Arm.Pivot;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkMax;

public interface IPivot {

    void setPower(double power);

    void setVoltage(double voltage);

    void setIdleMode(NeutralModeValue idleMode);

    void resetPosition(double position);

    void moveToAngle(double goalAngle);

    void updateInputs(PivotInputsAutoLogged inputs);
}
