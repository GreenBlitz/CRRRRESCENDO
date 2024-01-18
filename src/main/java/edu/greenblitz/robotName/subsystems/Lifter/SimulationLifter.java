package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;

public class SimulationLifter implements ILifter{

    private LifterInputs lastInputs = new LifterInputs();

    @Override
    public void setPower(double power) {
        lastInputs.power = power;
    }

    @Override
    public void setVoltage(double voltage) {
        lastInputs.voltage = voltage;
    }

    @Override
    public void setPosition(double position) {
        lastInputs.position = position;
    }

    @Override
    public boolean isMotorInPosition(double position) {
        return lastInputs.position == position;
    }

    @Override
    public void updateInputs(LifterInputs lastInputs) {

    }

    @Override
    public void stopMotor() {
        lastInputs.power = 0;
    }

    @Override
    public void setIdleMode(CANSparkMax.IdleMode mode) {
        lastInputs.idleMode = mode;
    }

}
