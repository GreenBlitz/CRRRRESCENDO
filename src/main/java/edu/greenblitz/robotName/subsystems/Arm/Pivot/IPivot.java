package edu.greenblitz.robotName.subsystems.Arm.Pivot;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkMax;

public interface IPivot {
    void resetPosition(double position);

    void setPower(double power);

    void moveToAngle(double goalAngle);

    void setVoltage(double voltage);

    void setIdleMode(NeutralModeValue idleMode);


    void updateInputs(PivotInputsAutoLogged inputs);
}
