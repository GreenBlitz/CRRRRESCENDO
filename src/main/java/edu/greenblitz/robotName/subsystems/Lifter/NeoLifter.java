package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.utils.DigitalInputMap;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class NeoLifter implements ILifter {
    private GBSparkMax motor;
    private LifterInputsAutoLogged lastInputs;
    public NeoLifter() {
        lastInputs = new LifterInputsAutoLogged();
        motor = new GBSparkMax(LifterConstants.MOTOR_PORT_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    }
    @Override
    public void setPower(double power) {
        motor.set(power);
        lastInputs.power = power;
    }
    @Override
    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
        lastInputs.voltage = voltage;
    }
    @Override
    public void resetEncoderTo(double position) {
        motor.getEncoder().setPosition(position);
        lastInputs.position = 0;
    }

    @Override
    public boolean isMotorInPosition(double position) {
        return motor.getEncoder().getPosition() <= position + LifterConstants.PID_TOLERANCE || motor.getEncoder().getPosition() >= position - LifterConstants.PID_TOLERANCE;
    }

    @Override
    public void updateInputs(LifterInputsAutoLogged lastInputs) {

    }
    @Override
    public void stopMotor() {
        this.setPower(0);
    }
    @Override
    public void setIdleMode(CANSparkMax.IdleMode mode) {
        motor.setIdleMode(mode);
    }

    @Override
    public double getPosition() {
        return motor.getEncoder().getPosition();
    }

    @Override
    public boolean isSwitchPressed() {
        return DigitalInputMap.getInstance().getValue(LifterConstants.SWITCH_ID);
    }
}
