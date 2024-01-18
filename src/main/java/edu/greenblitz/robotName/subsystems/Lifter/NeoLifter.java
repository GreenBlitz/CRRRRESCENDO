package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class NeoLifter implements ILifter {

    GBSparkMax motor;

    public NeoLifter(){
        motor = new GBSparkMax(LifterConstants.MOTOR_PORT_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    @Override
    public void setPower(double power) {
        motor.set(power);
    }

    @Override
    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    @Override
    public void setPosition(double position) {
        motor.getEncoder().setPosition(position);
    }

    @Override
    public boolean isMotorInPosition(double position) {
        return motor.getEncoder().getPosition()==position;
    }

    @Override
    public void updateInputs(LifterInputs lastInputs) {


    }
}
