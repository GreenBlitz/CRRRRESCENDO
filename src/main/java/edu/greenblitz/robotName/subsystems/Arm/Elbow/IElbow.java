package edu.greenblitz.robotName.subsystems.Arm.Elbow;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.signals.NeutralModeValue;

public interface IElbow {

    void resetPosition(double position);

    void setPower(double power);

    void moveToAngle(double goalAngle);

    void setVoltage(double voltage);

    void updateInputs(ElbowInputsAutoLogged inputs);

    void setIdleMode(NeutralModeValue idleMode);

}
