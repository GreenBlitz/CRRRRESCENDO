package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import com.ctre.phoenix6.signals.NeutralModeValue;

public interface IPivot {

    void setPower(double power);

    void setVoltage(double voltage);

    void setIdleMode(NeutralModeValue idleMode);

    void resetAngle(double position);

    void moveToAngle(double goalAngle);

    void updateInputs(PivotInputsAutoLogged inputs);
}
