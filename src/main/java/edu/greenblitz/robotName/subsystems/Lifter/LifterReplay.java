package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;

public class LifterReplay implements ILifter{


    @Override
    public void setPower(double power) {

    }

    @Override
    public void setVoltage(double voltage) {

    }

    @Override
    public void resetEncoderTo(double position) {

    }

    @Override
    public boolean isMotorInPosition(double position) {
        return false;
    }


    @Override
    public void updateInputs(LifterInputs lastInputs) {

    }

    @Override
    public void stopMotor() {

    }


    @Override
    public void setIdleMode(CANSparkMax.IdleMode mode) {

    }

    @Override
    public boolean isSwitchPressed() {
        return false;
    }
}
