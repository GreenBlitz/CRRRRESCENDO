package edu.greenblitz.robotName.subsystems.arm.elbow;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.geometry.Rotation2d;

public interface IElbow {

    void setPower(double power);

    void setVoltage(double voltage);

    void setIdleMode(NeutralModeValue idleMode);

    void resetAngle(Rotation2d position);

    void moveToAngle(Rotation2d targetAngle);

    void standInPlace(Rotation2d targetAngle);

    void updateInputs(ElbowInputsAutoLogged inputs);
}