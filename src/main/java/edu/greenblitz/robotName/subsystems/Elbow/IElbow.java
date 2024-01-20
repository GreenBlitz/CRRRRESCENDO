package edu.greenblitz.robotName.subsystems.Elbow;

import com.ctre.phoenix6.signals.NeutralModeValue;

public interface IElbow {

    void setPower(double power);

    void setVoltage(double voltage);

    void setIdleMode(NeutralModeValue idleMode);

    void resetAngle(double position);

    void moveToAngle(double goalAngle);

    void updateInputs(ElbowInputsAutoLogged inputs);

}
