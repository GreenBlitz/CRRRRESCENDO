package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.utils.DigitalInputMap;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class NeoLifter implements ILifter {
    private GBSparkMax motor;
    private LifterInputsAutoLogged inputs;

    public NeoLifter() {
        inputs = new LifterInputsAutoLogged();
        motor = new GBSparkMax(LifterConstants.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
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
    public void resetEncoder(double position) {
        motor.getEncoder().setPosition(position);
    }

    @Override
    public void stopMotor() {
        this.setPower(0);
    }

    @Override
    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        motor.setIdleMode(idleMode);
    }

    @Override
    public void setDestination(double destination) {
        inputs.destination = destination;
    }

    @Override
    public void updateInputs(LifterInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.position = motor.getEncoder().getPosition();
        inputs.velocity = motor.getEncoder().getVelocity();
        this.inputs.isSwitchPressed = DigitalInputMap.getInstance().getValue(LifterConstants.SWITCH_ID);
        inputs.isSwitchPressed = this.inputs.isSwitchPressed;
        inputs.destination = this.inputs.destination;
        this.inputs.isMotorAtPosition = Math.abs(motor.getEncoder().getPosition() - this.inputs.destination) <= LifterConstants.TOLERANCE;
        inputs.isMotorAtPosition = this.inputs.isMotorAtPosition;
    }
}
