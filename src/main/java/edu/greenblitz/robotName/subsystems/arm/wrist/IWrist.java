package edu.greenblitz.robotName.subsystems.arm.wrist;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;

public interface IWrist {

    void setPower(double power);

    void setVoltage(double voltage);

    void setIdleMode(CANSparkMax.IdleMode idleMode);

    void resetAngle(Rotation2d position);

    void resetEncoder();

    void moveToAngle(Rotation2d targetAngle);

    void standInPlace();

    void updateInputs(WristInputsAutoLogged inputs);
}
