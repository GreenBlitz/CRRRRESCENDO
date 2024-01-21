package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;

public class LifterReply implements ILifter{


    @Override
    public void setPowerForMotor1(double power) {

    }

    @Override
    public void setPowerForMotor2(double power) {

    }

    @Override
    public void setVoltageForMotor1(double voltage) {

    }

    @Override
    public void setVoltageForMotor2(double voltage) {

    }

    @Override
    public void setPositionForMotor1(double position) {

    }

    @Override
    public void setPositionForMotor2(double position) {

    }

    @Override
    public boolean isMotor1InPosition(double position) {
        return false;
    }

    @Override
    public boolean isMotor2InPosition(double position) {
        return false;
    }

    @Override
    public void updateInputs(LifterInputs lastInputs) {

    }

    @Override
    public void stopMotor1() {

    }

    @Override
    public void stopMotor2() {

    }

    @Override
    public void setIdleModeForMotor1(CANSparkMax.IdleMode mode) {

    }

    @Override
    public void setIdleModeForMotor2(CANSparkMax.IdleMode mode) {

    }

    @Override
    public double getPositionForMotor1() {
        return 0;
    }

    @Override
    public double getPositionForMotor2() {
        return 0;
    }
}
