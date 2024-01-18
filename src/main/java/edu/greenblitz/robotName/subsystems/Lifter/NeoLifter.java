package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class NeoLifter implements ILifter {

    GBSparkMax motor1;
    GBSparkMax motor2;

    public NeoLifter() {
        motor1 = new GBSparkMax(LifterConstants.MOTOR_PORT_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    @Override
    public void setPower(double power) {
        motor1.set(power);
    }

    @Override
    public void setVoltage(double voltage) {
        motor1.setVoltage(voltage);
    }

    @Override
    public void setPosition(double position) {
        motor1.getEncoder().setPosition(position);
    }

    @Override
    public boolean isMotorInPosition(double position) {
        return motor1.getEncoder().getPosition() == position;
    }

    @Override
    public void updateInputs(LifterInputs lastInputs) {
    }
    @Override
    public void stopMotor() {
        motor1.set(0);
    }
    @Override
    public void setIdleMode(CANSparkMax.IdleMode mode) {
        motor1.setIdleMode(mode);
    }
}
