package edu.greenblitz.robotName.subsystems.lifter;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;

public interface ILifter {

    void setPower(double power);

    void setVoltage(double voltage);

    void resetEncoder(double position);

    void stop();

    void setIdleMode(CANSparkMax.IdleMode idleMode);

    void goToPosition(double position);

    void openSolenoid();

    void closeSolenoid();

    void holdSolenoid();

    void setPowerToSolenoid(double powerSolenoid);

    void updateInputs(LifterInputsAutoLogged inputs);
}